package com.erp.school.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.erp.school.common.ApiResponse;
import com.erp.school.dto.StudentRequestDTO;
import com.erp.school.dto.StudentResponseDTO;
import com.erp.school.service.StudentServiceInt;
import com.erp.school.util.ValidationUtil;

import jakarta.validation.Valid;

/**
 * REST Controller for Student Management APIs.
 *
 * <p>
 * Handles student create, update, fetch, delete and enquiry conversion
 * operations.
 * </p>
 *
 * Base URL: /api/student
 *
 * @author Yashmita Rathore
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentServiceInt studentService;

	@Autowired
	private ValidationUtil validationUtil;

	/**
	 * Create new student.
	 *
	 * @param dto           student request data
	 * @param bindingResult validation result
	 * @return created student response
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> createStudent(@Valid @RequestBody StudentRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Create student request received for student: {}", dto.getStudentName());

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			logger.warn("Validation failed while creating student");
			return ResponseEntity.badRequest().body(validationResponse);
		}

		StudentResponseDTO response = studentService.createStudent(dto);

		logger.info("Student created successfully with ID: {}", response.getId());

		return ResponseEntity.ok(new ApiResponse(true, "Student created successfully", response));
	}

	/**
	 * Fetch all students.
	 *
	 * @return list of students
	 */
	@GetMapping
	public ResponseEntity<ApiResponse> getAllStudents() {

		logger.info("Fetch all students request received");

		List<StudentResponseDTO> response = studentService.getAllStudents();

		logger.info("Total students fetched: {}", response.size());

		return ResponseEntity.ok(new ApiResponse(true, "Students fetched successfully", response));
	}

	/**
	 * Fetch student by ID.
	 *
	 * @param id student ID
	 * @return student details
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getStudentById(@PathVariable Long id) {

		logger.info("Fetch student request received for ID: {}", id);

		StudentResponseDTO response = studentService.getStudentById(id);

		logger.info("Student fetched successfully for ID: {}", id);

		return ResponseEntity.ok(new ApiResponse(true, "Student fetched successfully", response));
	}

	/**
	 * Update student details.
	 *
	 * @param id            student ID
	 * @param dto           updated student data
	 * @param bindingResult validation result
	 * @return updated student response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO dto,
			BindingResult bindingResult) {

		logger.info("Update student request received for ID: {}", id);

		ApiResponse validationResponse = validationUtil.validate(bindingResult);

		if (!validationResponse.isSuccess()) {
			logger.warn("Validation failed while updating student ID: {}", id);
			return ResponseEntity.badRequest().body(validationResponse);
		}

		StudentResponseDTO response = studentService.updateStudent(id, dto);

		logger.info("Student updated successfully for ID: {}", id);

		return ResponseEntity.ok(new ApiResponse(true, "Student updated successfully", response));
	}

	/**
	 * Delete student by ID.
	 *
	 * @param id student ID
	 * @return success response
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id) {

		logger.info("Delete student request received for ID: {}", id);

		studentService.deleteStudent(id);

		logger.info("Student deleted successfully for ID: {}", id);

		return ResponseEntity.ok(new ApiResponse(true, "Student deleted successfully", null));
	}

	/**
	 * Convert enquiry into student admission.
	 *
	 * @param enquiryId enquiry ID
	 * @return created student from enquiry
	 */
	@PostMapping("/convert/{enquiryId}")
	public ResponseEntity<ApiResponse> convertEnquiryToStudent(@PathVariable Long enquiryId) {

		logger.info("Convert enquiry to student request received for enquiry ID: {}", enquiryId);

		StudentResponseDTO response = studentService.convertEnquiryToStudent(enquiryId);

		logger.info("Enquiry converted successfully. Enquiry ID: {}", enquiryId);

		return ResponseEntity.ok(new ApiResponse(true, "Enquiry converted to student successfully", response));
	}
}