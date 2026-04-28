package com.erp.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.erp.school.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// =========================
	// 1. RESOURCE NOT FOUND
	// =========================
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleNotFound(ResourceNotFoundException ex) {

		ApiResponse res = new ApiResponse(false);
		res.addMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}

	// =========================
	// 2. DUPLICATE RESOURCE
	// =========================
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ApiResponse> handleDuplicate(DuplicateResourceException ex) {

		ApiResponse res = new ApiResponse(false);
		res.addMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
	}

	// =========================
	// 3. BAD REQUEST
	// =========================
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiResponse> handleBadRequest(BadRequestException ex) {

		ApiResponse res = new ApiResponse(false);
		res.addMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	}

	// =========================
	// 4. VALIDATION ERROR (future use)
	// =========================
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleGeneral(Exception ex) {

		ApiResponse res = new ApiResponse(false);
		res.addMessage("Something went wrong!");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	}
}