package com.erp.school.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.school.dto.AcademicYearResponseDTO;
import com.erp.school.entity.AcademicYearEntity;
import com.erp.school.entityenum.Status;
import com.erp.school.exception.BadRequestException;
import com.erp.school.exception.ResourceNotFoundException;
import com.erp.school.repository.AcademicYearRepository;

@Service
@Transactional
public class AcademicYearCurrentService {

	private final AcademicYearRepository repo;

	public AcademicYearCurrentService(AcademicYearRepository repo) {
		this.repo = repo;
	}

	// ==================================================
	// GET CURRENT YEAR
	// ==================================================
	public AcademicYearEntity getCurrentEntity() {

		return repo.findByCurrentYearTrue().filter(year -> year.getStatus() == Status.ACTIVE)
				.orElseThrow(() -> new ResourceNotFoundException("Current academic year not found"));
	}

	// ==================================================
	// SET CURRENT YEAR
	// Only one current year allowed
	// ==================================================
	public void makeCurrent(Long id) {

		AcademicYearEntity newCurrent = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Academic year not found"));

		if (newCurrent.getStatus() != Status.ACTIVE) {
			throw new BadRequestException("Only ACTIVE academic year can be current");
		}

		// remove old current
		repo.findByCurrentYearTrue().ifPresent(old -> {
			old.setCurrentYear(false);
			repo.save(old);
		});

		// set new current
		newCurrent.setCurrentYear(true);
		repo.save(newCurrent);
	}

	// ==================================================
	// REMOVE CURRENT FLAG (optional use)
	// ==================================================
	public void clearCurrentYear() {

		repo.findByCurrentYearTrue().ifPresent(old -> {
			old.setCurrentYear(false);
			repo.save(old);
		});
	}
}