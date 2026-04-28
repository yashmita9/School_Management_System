package com.erp.school.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.erp.school.common.ApiResponse;
import com.erp.school.dto.TeacherAssignmentRequestDTO;
import com.erp.school.dto.TeacherAssignmentResponseDTO;
import com.erp.school.entityenum.Status;
import com.erp.school.service.TeacherAssignmentServiceInt;
import com.erp.school.util.ValidationUtil;

import jakarta.validation.Valid;

/**
 * Controller for Teacher Assignment APIs.
 * 
 * @author Yashmita Rathore
 */
@RestController
@RequestMapping("/api/teacher-assignments")
public class TeacherAssignmentController {

	private static final Logger logger = LoggerFactory.getLogger(TeacherAssignmentController.class);

	@Autowired
	private TeacherAssignmentServiceInt service;

	@Autowired
	private ValidationUtil validationUtil;

	@PostMapping
	public ResponseEntity<ApiResponse> assignTeacher(@Valid @RequestBody TeacherAssignmentRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Assign teacher request received");

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			return ResponseEntity.badRequest().body(validationResponse);
		}

		TeacherAssignmentResponseDTO response = service.assignTeacher(dto);

		return ResponseEntity.ok(new ApiResponse(true, "Teacher assigned successfully", response));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateAssignment(@PathVariable Long id,
			@Valid @RequestBody TeacherAssignmentRequestDTO dto, BindingResult bindingResult) {

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			return ResponseEntity.badRequest().body(validationResponse);
		}

		TeacherAssignmentResponseDTO response = service.updateAssignment(id, dto);

		return ResponseEntity.ok(new ApiResponse(true, "Assignment updated successfully", response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {

		TeacherAssignmentResponseDTO response = service.getById(id);

		return ResponseEntity.ok(new ApiResponse(true, "Assignment fetched successfully", response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {

		List<TeacherAssignmentResponseDTO> response = service.getAll();

		return ResponseEntity.ok(new ApiResponse(true, "Assignments fetched successfully", response));
	}

	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<ApiResponse> getByTeacher(@PathVariable Long teacherId) {

		List<TeacherAssignmentResponseDTO> response = service.getByTeacher(teacherId);

		return ResponseEntity.ok(new ApiResponse(true, "Teacher assignments fetched successfully", response));
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse> getByClassAndSection(@RequestParam Long classId, @RequestParam Long sectionId) {

		List<TeacherAssignmentResponseDTO> response = service.getByClassAndSection(classId, sectionId);

		return ResponseEntity.ok(new ApiResponse(true, "Data fetched successfully", response));
	}

	@GetMapping("/subject/{subjectId}")
	public ResponseEntity<ApiResponse> getBySubject(@PathVariable Long subjectId) {

		List<TeacherAssignmentResponseDTO> response = service.getBySubject(subjectId);

		return ResponseEntity.ok(new ApiResponse(true, "Subject assignments fetched successfully", response));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteAssignment(@PathVariable Long id) {

		service.deleteAssignment(id);

		return ResponseEntity.ok(new ApiResponse(true, "Assignment deleted successfully", null));
	}

	@PatchMapping("/{id}/toggle-status")
	public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id) {

		logger.info("Toggle status request received for assignment ID: {}", id);

		TeacherAssignmentResponseDTO response = service.toggleStatus(id);

		return ResponseEntity.ok(new ApiResponse(true, "Assignment status updated successfully", response));
	}
}