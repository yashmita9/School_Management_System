package com.erp.school.service;

import java.util.List;

import com.erp.school.common.PageResponseDTO;
import com.erp.school.dto.EnquiryRequestDTO;
import com.erp.school.dto.EnquiryResponseDTO;
import com.erp.school.entityenum.EnquiryStatus;

public interface EnquiryServiceInt {

	public EnquiryResponseDTO create(EnquiryRequestDTO enquiry);

	public EnquiryResponseDTO getById(Long id);

	public EnquiryResponseDTO update(Long id, EnquiryRequestDTO enquiry);

	public EnquiryResponseDTO updateStatus(Long id, EnquiryStatus status);

	public void deleteEnquiry(Long id);

	public List<EnquiryResponseDTO> getByStatus(EnquiryStatus status);

	PageResponseDTO<EnquiryResponseDTO> getEnquiries(int page, int size, String keyword, EnquiryStatus status,
			String source, String classInterested);
}