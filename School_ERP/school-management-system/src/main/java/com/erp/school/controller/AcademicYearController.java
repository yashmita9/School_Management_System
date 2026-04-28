package com.erp.school.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.erp.school.common.ApiResponse;
import com.erp.school.dto.AcademicYearRequestDTO;
import com.erp.school.dto.AcademicYearResponseDTO;
import com.erp.school.service.AcademicYearServiceInt;
import com.erp.school.util.ValidationUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/academic-years")
public class AcademicYearController {

	private final AcademicYearServiceInt service;
	private final ValidationUtil validationUtil;

	public AcademicYearController(AcademicYearServiceInt service, ValidationUtil validationUtil) {

		this.service = service;
		this.validationUtil = validationUtil;
	}

	// ================= CREATE =================
	@PostMapping
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody AcademicYearRequestDTO dto,
			BindingResult bindingResult) {

		ApiResponse validation = validationUtil.validate(bindingResult);

		if (!validation.isSuccess()) {
			return ResponseEntity.badRequest().body(validation);
		}

		AcademicYearResponseDTO response = service.create(dto);

		return ResponseEntity.ok(new ApiResponse(true, "Academic year created successfully", response));
	}

	// ================= UPDATE =================
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody AcademicYearRequestDTO dto,
			BindingResult bindingResult) {

		ApiResponse validation = validationUtil.validate(bindingResult);

		if (!validation.isSuccess()) {
			return ResponseEntity.badRequest().body(validation);
		}

		AcademicYearResponseDTO response = service.update(id, dto);

		return ResponseEntity.ok(new ApiResponse(true, "Academic year updated successfully", response));
	}

	// ================= DELETE =================
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {

		service.softDelete(id);

		return ResponseEntity.ok(new ApiResponse(true, "Academic year deleted successfully", null));
	}

	// ================= GET ACTIVE =================
	@GetMapping("/active")
	public ResponseEntity<ApiResponse> getActive() {

		List<AcademicYearResponseDTO> response = service.getActive();

		return ResponseEntity.ok(new ApiResponse(true, "Academic years fetched successfully", response));
	}

	// ================= GET CURRENT =================
	@GetMapping("/current")
	public ResponseEntity<ApiResponse> getCurrentYear() {

		AcademicYearResponseDTO response = service.getCurrentYear();

		return ResponseEntity.ok(new ApiResponse(true, "Current academic year fetched successfully", response));
	}

	// ================= SET CURRENT =================
	@PatchMapping("/{id}/current")
	public ResponseEntity<ApiResponse> setCurrent(@PathVariable Long id) {

		AcademicYearResponseDTO response = service.setCurrentYear(id);

		return ResponseEntity.ok(new ApiResponse(true, "Current academic year updated successfully", response));
	}

	// ================= TOGGLE STATUS =================
	@PatchMapping("/{id}/status")
	public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id) {

		AcademicYearResponseDTO response = service.toggleStatus(id);

		return ResponseEntity.ok(new ApiResponse(true, "Academic year status updated successfully", response));
	}
}