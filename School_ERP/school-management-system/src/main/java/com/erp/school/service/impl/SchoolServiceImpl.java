package com.erp.school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.erp.school.dto.SchoolRequestDTO;
import com.erp.school.dto.SchoolResponseDTO;
import com.erp.school.entity.SchoolEntity;
import com.erp.school.entityenum.Status;
import com.erp.school.exception.BadRequestException;
import com.erp.school.exception.ResourceNotFoundException;
import com.erp.school.repository.SchoolRepository;
import com.erp.school.service.FileStorageServiceInt;
import com.erp.school.service.SchoolServiceInt;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class for School operations (create, update, fetch). Handles business
 * logic and file upload integration.
 * 
 * @author Yashmita Rathore
 */
@Service
@Transactional
public class SchoolServiceImpl implements SchoolServiceInt {

	private static final Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private FileStorageServiceInt fileStorageService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Create new school.
	 *
	 * @param dto  school data
	 * @param file optional logo file
	 * @return saved school data
	 */
	@Override
	public SchoolResponseDTO createSchool(SchoolRequestDTO dto, MultipartFile file) {

		logger.info("Request received to create school with name: {}", dto.getSchoolName());

		if (schoolRepository.existsByStatus(Status.ACTIVE)) {
			logger.error("School creation failed - Active school already exists");
			throw new BadRequestException("School already exists!");
		}

		SchoolEntity school = modelMapper.map(dto, SchoolEntity.class);

		if (file != null && !file.isEmpty()) {
			logger.info("Uploading logo for school: {}", dto.getSchoolName());

			String logoPath = fileStorageService.uploadFile(file, "school");
			school.setLogoUrl(logoPath);

			logger.debug("Logo uploaded at path: {}", logoPath);
		}

		school.setStatus(Status.ACTIVE);

		SchoolEntity savedSchool = schoolRepository.save(school);

		logger.info("School created successfully with ID: {}", savedSchool.getId());

		return modelMapper.map(savedSchool, SchoolResponseDTO.class);
	}

	/**
	 * Update existing school.
	 *
	 * @param id   school ID
	 * @param dto  updated data
	 * @param file optional new logo
	 * @return updated school data
	 */
	@Override
	public SchoolResponseDTO updateSchool(Long id, SchoolRequestDTO dto, MultipartFile file) {

		logger.info("Request received to update school with ID: {}", id);

		SchoolEntity school = schoolRepository.findById(id).orElseThrow(() -> {
			logger.error("Update failed - School not found with ID: {}", id);
			return new ResourceNotFoundException("School not found");
		});

		modelMapper.map(dto, school);

		if (dto.getLogoUrl() != null) {
			logger.debug("Updating logo URL from DTO for school ID: {}", id);
			school.setLogoUrl(dto.getLogoUrl());
		}

		if (file != null && !file.isEmpty()) {

			logger.info("Uploading new logo for school ID: {}", id);

			if (school.getLogoUrl() != null) {
				logger.debug("Deleting old logo for school ID: {}", id);
				fileStorageService.deleteFile(school.getLogoUrl());
			}

			String newLogoPath = fileStorageService.uploadFile(file, "school");
			school.setLogoUrl(newLogoPath);

			logger.debug("New logo uploaded at path: {}", newLogoPath);
		}

		SchoolEntity updatedSchool = schoolRepository.save(school);

		logger.info("School updated successfully with ID: {}", updatedSchool.getId());

		return modelMapper.map(updatedSchool, SchoolResponseDTO.class);
	}

	/**
	 * Get active school.
	 *
	 * @return school data
	 */
	@Override
	public SchoolResponseDTO getSchool() {

		logger.info("Request received to fetch active school");

		SchoolEntity school = schoolRepository.findByStatus(Status.ACTIVE).orElseThrow(() -> {
			logger.error("Fetch failed - No active school found");
			return new ResourceNotFoundException("School not found");
		});

		logger.info("Active school fetched successfully with ID: {}", school.getId());

		return modelMapper.map(school, SchoolResponseDTO.class);
	}
}