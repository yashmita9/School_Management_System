package com.erp.school.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.school.common.ApiResponse;
import com.erp.school.common.PageResponseDTO;
import com.erp.school.constant.AppConstants;
import com.erp.school.dto.EnquiryRequestDTO;
import com.erp.school.dto.EnquiryResponseDTO;
import com.erp.school.entityenum.EnquiryStatus;
import com.erp.school.service.EnquiryServiceInt;
import com.erp.school.util.ValidationUtil;

import jakarta.validation.Valid;

/**
 * Controller for enquiry management APIs.
 */
@RestController
@RequestMapping(AppConstants.ENQUIRY)
public class EnquiryController {

	private static final Logger logger = LogManager.getLogger(EnquiryController.class);

	@Autowired
	private EnquiryServiceInt enquiryService;

	@Autowired
	private ValidationUtil validationUtil;

	/**
	 * Create enquiry.
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody EnquiryRequestDTO dto, BindingResult bindingResult) {

		logger.info("Create enquiry request received for student: {}", dto.getStudentName());

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			logger.warn("Validation failed while creating enquiry");
			return ResponseEntity.badRequest().body(validationResponse);
		}

		EnquiryResponseDTO response = enquiryService.create(dto);

		logger.info("Enquiry created successfully");

		return ResponseEntity.ok(new ApiResponse(true, "Enquiry created successfully", response));
	}

	/**
	 * Get all enquiries.
	 */
	@GetMapping
	public ResponseEntity<ApiResponse> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) EnquiryStatus status, @RequestParam(required = false) String source,
			@RequestParam(required = false) String classInterested) {

		logger.info("Fetch all enquiries request received");

		PageResponseDTO<EnquiryResponseDTO> response = enquiryService.getEnquiries(page, size, keyword, status, source, classInterested);

		return ResponseEntity.ok(new ApiResponse(true, "Enquiries fetched successfully", response));
	}

	/**
	 * Get enquiry by id.
	 */
	@GetMapping(AppConstants.ENQUIRY_BY_ID)
	public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {

		logger.info("Get enquiry request for ID: {}", id);

		EnquiryResponseDTO response = enquiryService.getById(id);

		return ResponseEntity.ok(new ApiResponse(true, "Enquiry fetched successfully", response));
	}

	/**
	 * Update enquiry.
	 */
	@PutMapping(AppConstants.ENQUIRY_BY_ID)
	public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody EnquiryRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Update enquiry request received for ID: {}", id);

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			logger.warn("Validation failed while updating enquiry ID: {}", id);
			return ResponseEntity.badRequest().body(validationResponse);
		}

		EnquiryResponseDTO response = enquiryService.update(id, dto);

		logger.info("Enquiry updated successfully for ID: {}", id);

		return ResponseEntity.ok(new ApiResponse(true, "Enquiry updated successfully", response));
	}

	/**
	 * Update enquiry status.
	 */
	@PatchMapping(AppConstants.ENQUIRY_STATUS_UPDATE)
	public ResponseEntity<ApiResponse> updateStatus(@PathVariable Long id, @RequestParam EnquiryStatus status) {

		logger.info("Update status request for ID: {} to {}", id, status);

		EnquiryResponseDTO response = enquiryService.updateStatus(id, status);

		return ResponseEntity.ok(new ApiResponse(true, "Status updated successfully", response));
	}

	/**
	 * Soft delete enquiry.
	 */
	@DeleteMapping(AppConstants.ENQUIRY_BY_ID)
	public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {

		logger.info("Delete enquiry request received for ID: {}", id);

		enquiryService.deleteEnquiry(id);

		logger.info("Enquiry marked as LOST for ID: {}", id);

		return ResponseEntity.ok(new ApiResponse(true, "Enquiry marked as LOST successfully", null));
	}

	/**
	 * Get enquiries by status.
	 */
	@GetMapping(AppConstants.ENQUIRY_BY_STATUS)
	public ResponseEntity<ApiResponse> getByStatus(@PathVariable EnquiryStatus status) {

		logger.info("Get enquiries by status: {}", status);

		List<EnquiryResponseDTO> response = enquiryService.getByStatus(status);

		return ResponseEntity.ok(new ApiResponse(true, "Enquiries fetched successfully", response));
	}
}