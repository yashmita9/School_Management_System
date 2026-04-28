package com.erp.school.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.school.dto.ClassRequestDTO;
import com.erp.school.dto.ClassResponseDTO;
import com.erp.school.dto.SectionRequestDTO;
import com.erp.school.entity.ClassEntity;
import com.erp.school.entity.SectionEntity;
import com.erp.school.entityenum.Status;
import com.erp.school.exception.BadRequestException;
import com.erp.school.exception.ResourceNotFoundException;
import com.erp.school.repository.ClassRepository;
import com.erp.school.service.ClassServiceInt;

import jakarta.transaction.Transactional;

/**
 * Service implementation for Class management.
 * 
 * Handles: - Create (Single & Bulk) - Update - Soft Delete - Fetch Active
 * Classes - Toggle Status
 */
@Transactional
@Service
public class ClassServiceImpl implements ClassServiceInt {

	private static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Autowired
	private ClassRepository repo;

	@Autowired
	private ModelMapper mapper;

	/**
	 * Create a single class.
	 *
	 * @param dto class request data
	 * @return created class response
	 */
	@Override
	public ClassResponseDTO create(ClassRequestDTO dto) {

		if (repo.existsByClassName(dto.getClassName())) {
			throw new BadRequestException("Class already exists");
		}
		if (repo.existsByDisplayOrder(dto.getDisplayOrder())) {
			throw new BadRequestException("Display order already exists");
		}

		// Class mapping via ModelMapper
		ClassEntity entity = mapper.map(dto, ClassEntity.class);
		entity.setStatus(Status.ACTIVE);

		// IMPORTANT: override sections manually
		entity.setSections(mapSections(dto, entity));

		ClassEntity saved = repo.save(entity);

		return mapper.map(saved, ClassResponseDTO.class);
	}

	/**
	 * Create multiple classes in bulk.
	 *
	 * @param list list of class request DTOs
	 * @return list of created class responses
	 */
	@Override
	public List<ClassResponseDTO> createBulk(List<ClassRequestDTO> list) {

		logger.info("Bulk create started. Count: {}", list.size());

		// duplicate check in request
		Set<String> names = new HashSet<>();
		for (ClassRequestDTO dto : list) {

			if (!names.add(dto.getClassName())) {
				throw new BadRequestException("Duplicate in request: " + dto.getClassName());
			}

			if (repo.existsByClassName(dto.getClassName())) {
				throw new BadRequestException("Already exists: " + dto.getClassName());
			}
		}

		List<ClassEntity> entities = new ArrayList<>();

		for (ClassRequestDTO dto : list) {

			ClassEntity entity = mapper.map(dto, ClassEntity.class);

			entity.setStatus(Status.ACTIVE);
			entity.setSections(mapSections(dto, entity));

			entities.add(entity);
		}

		List<ClassResponseDTO> response = repo.saveAll(entities).stream()
				.map(e -> mapper.map(e, ClassResponseDTO.class)).toList();

		logger.info("Bulk create completed. Count: {}", response.size());

		return response;
	}

	/**
	 * Update class by ID.
	 *
	 * @param id  class ID
	 * @param dto updated class data
	 * @return updated class response
	 */
	@Override
	public ClassResponseDTO update(Long id, ClassRequestDTO dto) {

		ClassEntity entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Class not found"));

		if (!entity.getClassName().equalsIgnoreCase(dto.getClassName()) && repo.existsByClassName(dto.getClassName())) {
			throw new BadRequestException("Class already exists");
		}

		if (!entity.getDisplayOrder().equals(dto.getDisplayOrder())
				&& repo.existsByDisplayOrder(dto.getDisplayOrder())) {
			throw new BadRequestException("Display order already exists");
		}
		entity.setClassName(dto.getClassName());
		entity.setDescription(dto.getDescription());
		entity.setDisplayOrder(dto.getDisplayOrder());

		entity.getSections().clear();
		entity.getSections().addAll(mapSections(dto, entity));

		ClassEntity updated = repo.save(entity);

		return mapper.map(updated, ClassResponseDTO.class);
	}

	/**
	 * Soft delete class by ID.
	 *
	 * @param id class ID
	 */
	@Override
	public void softDelete(Long id) {

		logger.info("Soft deleting class ID: {}", id);

		ClassEntity entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Class not found"));
		entity.setStatus(Status.DELETED);
		entity.setDeletedAt(LocalDateTime.now());

		if (entity.getSections() != null && !entity.getSections().isEmpty()) {

			entity.getSections().forEach(section -> {
				section.setStatus(Status.DELETED);
				entity.setDeletedAt(LocalDateTime.now());

			});
		}

		repo.save(entity);

		logger.info("Class soft deleted successfully ID: {}", id);
	}

	/**
	 * Fetch all active classes.
	 *
	 * @return list of active classes
	 */
	// ========================= GET ACTIVE =========================
	@Override
	public List<ClassResponseDTO> getActive() {

		return repo.findByStatus(Status.ACTIVE).stream().map(e -> mapper.map(e, ClassResponseDTO.class)).toList();
	}

	/**
	 * Toggle class status (ACTIVE ↔ INACTIVE).
	 *
	 * @param id class ID
	 * @return updated class response
	 */
	// ========================= TOGGLE STATUS =========================
	@Override
	public ClassResponseDTO toggleStatus(Long id) {

		ClassEntity entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Class not found"));

		if (entity.getStatus() == Status.DELETED) {
			throw new BadRequestException("Deleted class cannot be toggled");
		}

		entity.setStatus(entity.getStatus() == Status.ACTIVE ? Status.INACTIVE : Status.ACTIVE);

		return mapper.map(repo.save(entity), ClassResponseDTO.class);
	}

	private List<SectionEntity> mapSections(ClassRequestDTO dto, ClassEntity entity) {

		List<SectionEntity> sectionList = new ArrayList<>();

		if (dto.getSections() == null) {
			return sectionList;
		}

		Set<String> unique = new HashSet<>();

		for (SectionRequestDTO secDto : dto.getSections()) {

			String name = secDto.getSectionName().trim().toUpperCase();

			if (!unique.add(name)) {
				throw new BadRequestException("Duplicate section: " + secDto.getSectionName());
			}

			SectionEntity section = new SectionEntity();
			section.setSectionName(secDto.getSectionName());
			section.setStatus(Status.ACTIVE);

			section.setClassEntity(entity);

			sectionList.add(section);
		}

		return sectionList;
	}
}