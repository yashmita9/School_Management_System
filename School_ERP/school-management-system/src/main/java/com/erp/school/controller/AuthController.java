package com.erp.school.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.school.common.ApiResponse;
import com.erp.school.constant.AppConstants;
import com.erp.school.dto.AuthRequestDTO;
import com.erp.school.dto.AuthResponseDTO;
import com.erp.school.service.impl.AuthServiceImpl;
import com.erp.school.util.ValidationUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.AUTH)
public class AuthController {

	private static final Logger logger = LogManager.getLogger(AuthController.class);

	@Autowired
	private AuthServiceImpl authService;

	@Autowired
	private ValidationUtil validationUtil;

	/**
	 * Handles user login request.
	 *
	 * This API accepts login credentials, validates user authentication, and
	 * returns JWT token with user details.
	 *
	 * @param request contains email and password
	 * @return ResponseEntity containing login success response
	 */
	@PostMapping(AppConstants.LOGIN)
	public ResponseEntity<ApiResponse> login(@Valid @RequestBody AuthRequestDTO request, BindingResult bindingResult) {

		logger.info("Login API request received for email: {}", request.getEmail());

		ApiResponse validationResponse = validationUtil.validate(bindingResult);
		if (!validationResponse.isSuccess()) {
			return ResponseEntity.badRequest().body(validationResponse);
		}

		AuthResponseDTO response = authService.login(request);

		logger.info("Login API executed successfully for email: {}", request.getEmail());

		return ResponseEntity.ok(new ApiResponse(true, "Login successful", response));
	}
}