package com.erp.school.service;

import org.springframework.web.multipart.MultipartFile;

import com.erp.school.common.ApiResponse;
import com.erp.school.dto.SchoolRequestDTO;
import com.erp.school.dto.SchoolResponseDTO;

public interface SchoolServiceInt {

	SchoolResponseDTO createSchool(SchoolRequestDTO dto, MultipartFile file);

	SchoolResponseDTO updateSchool(Long id, SchoolRequestDTO dto, MultipartFile file);

	SchoolResponseDTO getSchool();
}
