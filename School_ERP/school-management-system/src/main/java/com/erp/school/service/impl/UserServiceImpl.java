package com.erp.school.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.erp.school.common.BaseServiceImpl;
import com.erp.school.common.PageResponseDTO;
import com.erp.school.dto.ChangeRequestDto;
import com.erp.school.dto.UserRequestDTO;
import com.erp.school.dto.UserResponseDTO;
import com.erp.school.entity.UserEntity;
import com.erp.school.entityenum.Status;
import com.erp.school.entityenum.UserRole;
import com.erp.school.exception.BadRequestException;
import com.erp.school.exception.ResourceNotFoundException;
import com.erp.school.repository.UserRepository;
import com.erp.school.service.UserService;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;

/**
 * Service implementation for user management operations.
 * 
 * @author Yashmita Rathore
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserEntity, UserRequestDTO, UserResponseDTO>
		implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		super(userRepository, modelMapper);
		this.userRepository = userRepository;
	}

	@Override
	protected Class<UserEntity> getEntityClass() {
		return UserEntity.class;
	}

	@Override
	protected Class<UserResponseDTO> getDtoClass() {
		return UserResponseDTO.class;
	}

	/**
	 * Toggle user active/inactive status.
	 */
	@Override
	public UserResponseDTO toggleStatus(Long id) {

		logger.info("Toggle status request for id: {}", id);

		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user.setStatus(user.getStatus() == Status.ACTIVE ? Status.INACTIVE : Status.ACTIVE);

		userRepository.save(user);

		return modelMapper.map(user, UserResponseDTO.class);
	}

	/**
	 * Soft delete user.
	 */
	@Override
	public void deleteUser(Long id) {

		logger.info("Delete user request for id: {}", id);

		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user.setStatus(Status.DELETED);
		user.setDeletedAt(LocalDateTime.now());

		userRepository.save(user);

		logger.info("User deleted successfully id: {}", id);
	}

	/**
	 * Get all active users.
	 */
	public List<UserResponseDTO> getAllUsers() {

		logger.info("Fetching all users");

		List<UserEntity> entities = userRepository.findAllUsers();

		return entities.stream().map(entity -> modelMapper.map(entity, UserResponseDTO.class)).toList();
	}

	/**
	 * Get active user by id.
	 */
	public UserEntity getUserById(Long id) {

		return userRepository.findActiveUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	/**
	 * Validate user before creation.
	 */
	@Override
	public void validateBeforeCreate(UserRequestDTO dto) {

		if (userRepository.existsByPhone(dto.getPhone())) {
			throw new BadRequestException("Phone number already exists");
		}

		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new BadRequestException("Email already exists");
		}
	}

	/**
	 * Before saving user.
	 */
	@Override
	public void beforeSave(UserEntity entity, UserRequestDTO request) {

		entity.setFirstLogin(true);
		entity.setPassword(passwordEncoder.encode(request.getPassword()));
	}

	/**
	 * Change user password.
	 */
	@Override
	public void changePassword(ChangeRequestDto request) {

		logger.info("Change password request for email: {}", request.getEmail());

		UserEntity user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new BadRequestException("User not found"));

		if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
			throw new BadRequestException("Old password is incorrect");
		}

		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		user.setFirstLogin(false);

		userRepository.save(user);

		logger.info("Password changed successfully for email: {}", request.getEmail());
	}

	/**
	 * Get logged-in user profile.
	 */
	@Override
	public UserResponseDTO getProfile() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String email = auth.getName();

		UserEntity user = userRepository.findByEmail(email)
				.orElseThrow(() -> new BadRequestException("User not found"));

		return modelMapper.map(user, UserResponseDTO.class);
	}

	/**
	 * Update user details.
	 */
	@Override
	public UserResponseDTO update(Long id, UserRequestDTO request) {

		logger.info("Update request for user id: {}", id);

		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

		Optional<UserEntity> emailUser = userRepository.findByEmail(request.getEmail());

		if (emailUser.isPresent() && !emailUser.get().getId().equals(id)) {
			throw new BadRequestException("Email already exists");
		}

		Optional<UserEntity> phoneUser = userRepository.findByPhone(request.getPhone());

		if (phoneUser.isPresent() && !phoneUser.get().getId().equals(id)) {
			throw new BadRequestException("Phone number already exists");
		}

		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user.setRole(request.getRole());

		if (request.getPassword() != null && !request.getPassword().isBlank()) {
			user.setPassword(passwordEncoder.encode(request.getPassword()));
		}

		UserEntity updatedUser = userRepository.save(user);

		logger.info("User updated successfully id: {}", id);

		return modelMapper.map(updatedUser, UserResponseDTO.class);
	}

	@Override
	public PageResponseDTO<UserResponseDTO> getUsers(int page, int size, String keyword, UserRole role, Status status) {

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

		Specification<UserEntity> spec = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			predicates.add(cb.isNull(root.get("deletedAt")));

			if (keyword != null && !keyword.trim().isEmpty()) {

				String search = "%" + keyword.trim().toLowerCase() + "%";

				Predicate p1 = cb.like(cb.lower(root.get("firstName")), search);
				Predicate p2 = cb.like(cb.lower(root.get("lastName")), search);
				Predicate p3 = cb.like(cb.lower(root.get("email")), search);
				Predicate p4 = cb.like(root.get("phone"), "%" + keyword + "%");

				predicates.add(cb.or(p1, p2, p3, p4));
			}

			if (role != null) {
				predicates.add(cb.equal(root.get("role"), role));
			}

			if (status != null) {
				predicates.add(cb.equal(root.get("status"), status));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		Page<UserEntity> userPage = userRepository.findAll(spec, pageable);

		List<UserResponseDTO> users = userPage.getContent().stream()
				.map(user -> modelMapper.map(user, UserResponseDTO.class)).toList();

		PageResponseDTO<UserResponseDTO> response = new PageResponseDTO<>();

		response.setContent(users);
		response.setPage(userPage.getNumber());
		response.setSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLast(userPage.isLast());

		return response;
	}
}