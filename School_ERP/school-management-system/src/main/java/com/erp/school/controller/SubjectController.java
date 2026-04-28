package com.erp.school.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.erp.school.dto.SubjectRequestDTO;
import com.erp.school.dto.SubjectResponseDTO;
import com.erp.school.entityenum.Status;
import com.erp.school.service.SubjectServiceInt;
import com.erp.school.util.ValidationUtil;

import jakarta.validation.Valid;

/**
 * Controller for Subject Module APIs.
 * 
 * @author Yashmita Rathore
 */
@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

	private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

	@Autowired
	private SubjectServiceInt subjectService;

	@Autowired
	private ValidationUtil validationUtil;

	@PostMapping
	public ResponseEntity<ApiResponse> addSubject(@Valid @RequestBody SubjectRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Add subject request received");

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			return ResponseEntity.badRequest().body(validationResponse);
		}

		SubjectResponseDTO response = subjectService.addSubject(dto);

		return ResponseEntity.ok(new ApiResponse(true, "Subject added successfully", response));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateSubject(@PathVariable Long id, @Valid @RequestBody SubjectRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Update subject request received for ID: {}", id);

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			return ResponseEntity.badRequest().body(validationResponse);
		}

		SubjectResponseDTO response = subjectService.updateSubject(id, dto);

		return ResponseEntity.ok(new ApiResponse(true, "Subject updated successfully", response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {

		SubjectResponseDTO response = subjectService.getById(id);

		return ResponseEntity.ok(new ApiResponse(true, "Subject fetched successfully", response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) Status status) {

		PageResponseDTO<SubjectResponseDTO> data = subjectService.getSubjects(page, size, keyword, status);

		return ResponseEntity.ok(new ApiResponse(true, "Subjects fetched successfully", data));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteSubject(@PathVariable Long id) {

		subjectService.deleteSubject(id);

		return ResponseEntity.ok(new ApiResponse(true, "Subject deleted successfully", null));
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<ApiResponse> updateStatus(@PathVariable Long id, @RequestParam Status status) {

		SubjectResponseDTO response = subjectService.updateStatus(id, status);

		return ResponseEntity.ok(new ApiResponse(true, "Status updated successfully", response));
	}

}