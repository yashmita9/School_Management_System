package com.erp.school.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.erp.school.common.ApiResponse;
import com.erp.school.constant.AppConstants;
import com.erp.school.dto.SchoolRequestDTO;
import com.erp.school.dto.SchoolResponseDTO;
import com.erp.school.service.FileStorageServiceInt;
import com.erp.school.service.SchoolServiceInt;
import com.erp.school.util.ValidationUtil;

import jakarta.validation.Valid;

/**
 * Controller for School management APIs.
 * 
 * @author Yashmita Rathore
 */
@RestController
@RequestMapping(AppConstants.SCHOOL)
public class SchoolController {

	private static final Logger logger = LogManager.getLogger(SchoolController.class);

	@Autowired
	private SchoolServiceInt schoolService;

	@Autowired
	private ValidationUtil validationUtil;

	@Autowired
	private FileStorageServiceInt fileStorage;

	/**
	 * Upload school logo.
	 */
	@PostMapping(AppConstants.SCHOOL_UPLOAD_LOGO)
	public ResponseEntity<ApiResponse> uploadLogo(@RequestParam("file") MultipartFile file) {

		logger.info("Upload logo request received");

		validationUtil.validateFile(file);

		String filePath = fileStorage.uploadFile(file, "school");

		logger.info("File uploaded successfully");

		return ResponseEntity.ok(new ApiResponse(true, "File uploaded successfully", filePath));
	}

	/**
	 * Create school.
	 */
	@PostMapping(AppConstants.SCHOOL_CREATE)
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody SchoolRequestDTO dto, BindingResult bindingResult) {

		logger.info("Create school request received");

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			logger.warn("Create school validation failed");
			return ResponseEntity.badRequest().body(validationResponse);
		}

		SchoolResponseDTO response = schoolService.createSchool(dto, null);

		logger.info("School created successfully");

		return ResponseEntity.ok(new ApiResponse(true, "School created successfully", response));
	}

	/**
	 * Update school without file.
	 */
	@PutMapping(AppConstants.SCHOOL_UPDATE)
	public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody SchoolRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Update request received for ID: {}", id);

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			logger.warn("Validation failed for update ID: {}", id);
			return ResponseEntity.badRequest().body(validationResponse);
		}

		SchoolResponseDTO response = schoolService.updateSchool(id, dto, null);

		logger.info("School updated successfully ID: {}", id);

		return ResponseEntity.ok(new ApiResponse(true, "School updated successfully", response));
	}

	/**
	 * Get school details.
	 */
	@GetMapping
	public ResponseEntity<ApiResponse> get() {

		logger.info("Get school request received");

		SchoolResponseDTO response = schoolService.getSchool();

		logger.info("School fetched successfully");

		return ResponseEntity.ok(new ApiResponse(true, "School fetched successfully", response));
	}
}