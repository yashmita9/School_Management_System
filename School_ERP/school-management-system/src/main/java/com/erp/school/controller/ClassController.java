package com.erp.school.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.school.common.ApiResponse;
import com.erp.school.constant.AppConstants;
import com.erp.school.dto.ClassRequestDTO;
import com.erp.school.dto.ClassResponseDTO;
import com.erp.school.service.ClassServiceInt;
import com.erp.school.util.ValidationUtil;

import jakarta.validation.Valid;

/**
 * Controller for Class management APIs.
 */
@RestController
@RequestMapping(AppConstants.CLASSES)
@Validated
public class ClassController {

	private static final Logger logger = LogManager.getLogger(ClassController.class);

	@Autowired
	private ClassServiceInt service;

	@Autowired
	private ValidationUtil validationUtil;

	/**
	 * Create new class.
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> create(
			@Valid @RequestBody ClassRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Create class request received: {}", dto.getClassName());

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			logger.warn("Validation failed for create class");
			return ResponseEntity.badRequest().body(validationResponse);
		}

		ClassResponseDTO response = service.create(dto);

		logger.info("Class created successfully. ID: {}", response.getId());

		return ResponseEntity.ok(
				new ApiResponse(true, "Class created successfully", response));
	}

	/**
	 * Bulk create classes.
	 */
	@PostMapping(AppConstants.CLASS_BULK)
	public ResponseEntity<ApiResponse> bulkCreate(
			@Valid @RequestBody List<@Valid ClassRequestDTO> list,
			BindingResult bindingResult) {

		logger.info("Bulk create request received. Count: {}", list.size());

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			logger.warn("Validation failed for bulk create");
			return ResponseEntity.badRequest().body(validationResponse);
		}

		List<ClassResponseDTO> response = service.createBulk(list);

		logger.info("Bulk classes created successfully. Count: {}", response.size());

		return ResponseEntity.ok(
				new ApiResponse(true, "Bulk created successfully", response));
	}

	/**
	 * Update class by id.
	 */
	@PutMapping(AppConstants.CLASS_BY_ID)
	public ResponseEntity<ApiResponse> update(
			@PathVariable Long id,
			@Valid @RequestBody ClassRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Update request received for class ID: {}", id);

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			logger.warn("Validation failed for update class ID: {}", id);
			return ResponseEntity.badRequest().body(validationResponse);
		}

		ClassResponseDTO response = service.update(id, dto);

		logger.info("Class updated successfully. ID: {}", id);

		return ResponseEntity.ok(
				new ApiResponse(true, "Class updated successfully", response));
	}

	/**
	 * Soft delete class.
	 */
	@DeleteMapping(AppConstants.CLASS_BY_ID)
	public ResponseEntity<ApiResponse> softDelete(@PathVariable Long id) {

		logger.info("Soft delete request received for ID: {}", id);

		service.softDelete(id);

		logger.info("Class soft deleted successfully. ID: {}", id);

		return ResponseEntity.ok(
				new ApiResponse(true, "Class deleted successfully", null));
	}

	/**
	 * Get all active classes.
	 */
	@GetMapping(AppConstants.CLASS_ACTIVE)
	public ResponseEntity<ApiResponse> getActive() {

		logger.info("Fetch active classes request received");

		List<ClassResponseDTO> response = service.getActive();

		return ResponseEntity.ok(
				new ApiResponse(true, "Active classes fetched successfully", response));
	}

	/**
	 * Toggle class status.
	 */
	@PatchMapping(AppConstants.CLASS_TOGGLE_STATUS)
	public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id) {

		logger.info("Toggle status request received for ID: {}", id);

		ClassResponseDTO response = service.toggleStatus(id);

		return ResponseEntity.ok(
				new ApiResponse(true, "Status updated successfully", response));
	}
}