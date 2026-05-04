package com.erp.school.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.erp.school.common.ApiResponse;
import com.erp.school.dto.StudentEnrollmentRequestDTO;
import com.erp.school.dto.StudentEnrollmentResponseDTO;
import com.erp.school.service.StudentEnrollmentServiceInt;
import com.erp.school.util.ValidationUtil;

import jakarta.validation.Valid;

/**
 * Controller class for Student Enrollment APIs. Handles class / section /
 * academic year mapping.
 *
 * @author Yashmita Rathore
 */
@RestController
@RequestMapping("/api/student-enrollment")
public class StudentEnrollmentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentEnrollmentController.class);

	@Autowired
	private StudentEnrollmentServiceInt studentEnrollmentService;

	@Autowired
	private ValidationUtil validationUtil;

	// ================= ASSIGN STUDENT =================
	@PostMapping
	public ResponseEntity<ApiResponse> assignStudent(@Valid @RequestBody StudentEnrollmentRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Assign student request received for student ID: {}", dto.getStudentId());

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			return ResponseEntity.badRequest().body(validationResponse);
		}

		StudentEnrollmentResponseDTO response = studentEnrollmentService.assignStudent(dto);

		return ResponseEntity.ok(new ApiResponse(true, "Student assigned successfully", response));
	}

	// ================= CURRENT ENROLLMENT =================
	@GetMapping("/current/{studentId}")
	public ResponseEntity<ApiResponse> getCurrentEnrollment(@PathVariable Long studentId) {

		logger.info("Fetch current enrollment request for student ID: {}", studentId);

		StudentEnrollmentResponseDTO response = studentEnrollmentService.getCurrentEnrollment(studentId);

		return ResponseEntity.ok(new ApiResponse(true, "Current enrollment fetched successfully", response));
	}

	// ================= HISTORY =================
	@GetMapping("/history/{studentId}")
	public ResponseEntity<ApiResponse> getStudentHistory(@PathVariable Long studentId) {

		logger.info("Fetch history request for student ID: {}", studentId);

		List<StudentEnrollmentResponseDTO> response = studentEnrollmentService.getStudentHistory(studentId);

		return ResponseEntity.ok(new ApiResponse(true, "Student history fetched successfully", response));
	}

	// ================= BY CLASS =================
	@GetMapping("/class/{classId}")
	public ResponseEntity<ApiResponse> getStudentsByClass(@PathVariable Long classId) {

		logger.info("Fetch students request by class ID: {}", classId);

		List<StudentEnrollmentResponseDTO> response = studentEnrollmentService.getStudentsByClass(classId);

		return ResponseEntity.ok(new ApiResponse(true, "Students fetched successfully", response));
	}

	// ================= BY SECTION =================
	@GetMapping("/section/{sectionId}")
	public ResponseEntity<ApiResponse> getStudentsBySection(@PathVariable Long sectionId) {

		logger.info("Fetch students request by section ID: {}", sectionId);

		List<StudentEnrollmentResponseDTO> response = studentEnrollmentService.getStudentsBySection(sectionId);

		return ResponseEntity.ok(new ApiResponse(true, "Students fetched successfully", response));
	}

	// ================= PROMOTE STUDENT =================
	@PostMapping("/promote")
	public ResponseEntity<ApiResponse> promoteStudent(@Valid @RequestBody StudentEnrollmentRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Promote student request received for student ID: {}", dto.getStudentId());

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			return ResponseEntity.badRequest().body(validationResponse);
		}

		StudentEnrollmentResponseDTO response = studentEnrollmentService.promoteStudent(dto);

		return ResponseEntity.ok(new ApiResponse(true, "Student promoted successfully", response));
	}

	// ================= TRANSFER SECTION =================
	@PatchMapping("/{enrollmentId}/section/{sectionId}")
	public ResponseEntity<ApiResponse> transferSection(@PathVariable Long enrollmentId, @PathVariable Long sectionId) {

		logger.info("Transfer section request for enrollment ID: {}", enrollmentId);

		StudentEnrollmentResponseDTO response = studentEnrollmentService.transferSection(enrollmentId, sectionId);

		return ResponseEntity.ok(new ApiResponse(true, "Section transferred successfully", response));
	}

	// ================= BY CLASS + SECTION =================
	@GetMapping("/class-section")
	public ResponseEntity<ApiResponse> getStudentsByClassAndSection(@RequestParam Long classId,
			@RequestParam Long sectionId) {

		logger.info("Fetch students request for classId: {} and sectionId: {}", classId, sectionId);

		List<StudentEnrollmentResponseDTO> response = studentEnrollmentService.getStudentsByClassAndSection(classId,
				sectionId);

		if (response.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse(true, "No students found for this class and section", response));
		}

		return ResponseEntity.ok(new ApiResponse(true, "Students fetched successfully", response));
	}
}