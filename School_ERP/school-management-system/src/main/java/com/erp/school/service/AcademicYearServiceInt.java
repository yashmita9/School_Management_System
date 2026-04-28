package com.erp.school.service;

import java.util.List;

import com.erp.school.dto.AcademicYearRequestDTO;
import com.erp.school.dto.AcademicYearResponseDTO;

public interface AcademicYearServiceInt {

	AcademicYearResponseDTO create(AcademicYearRequestDTO dto);

	AcademicYearResponseDTO update(Long id, AcademicYearRequestDTO dto);

	void softDelete(Long id);

	List<AcademicYearResponseDTO> getActive();

	AcademicYearResponseDTO getCurrentYear();

	AcademicYearResponseDTO setCurrentYear(Long id);

	AcademicYearResponseDTO toggleStatus(Long id);
	
	

}
