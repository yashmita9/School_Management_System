package com.erp.school.service.impl;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.school.config.JwtUtil;
import com.erp.school.dto.AuthRequestDTO;
import com.erp.school.dto.AuthResponseDTO;
import com.erp.school.entity.UserEntity;
import com.erp.school.entityenum.Status;
import com.erp.school.exception.BadRequestException;
import com.erp.school.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthServiceImpl {

	private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * Authenticates user login request.
	 *
	 * This method validates: 1. Email existence 2. User active status 3. Password
	 * match 4. Updates last login time 5. Generates JWT token
	 *
	 * @param request contains email and password
	 * @return AuthResponseDTO containing token, role and first login flag
	 * @throws BadRequestException when credentials are invalid
	 */
	@Transactional
	public AuthResponseDTO login(AuthRequestDTO request) {

		logger.info("Login attempt started for email: {}", request.getEmail());

		UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
			logger.error("Login failed. Email not found: {}", request.getEmail());
			return new BadRequestException("Invalid email or password");
		});

		if (user.getStatus() != Status.ACTIVE) {
			logger.warn("Login blocked. User is not active: {}", request.getEmail());
			throw new BadRequestException("Invalid email or password");
		}

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			logger.error("Login failed. Incorrect password for email: {}", request.getEmail());
			throw new BadRequestException("Invalid email or password");
		}

		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);

		logger.info("Last login time updated for user: {}", request.getEmail());

		String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

		logger.info("JWT token generated successfully for user: {}", request.getEmail());

		return new AuthResponseDTO(token, user.getRole().name(), user.isFirstLogin());
	}
}