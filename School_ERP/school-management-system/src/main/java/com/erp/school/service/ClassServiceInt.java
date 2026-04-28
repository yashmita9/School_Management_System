package com.erp.school.service;

import java.util.List;

import com.erp.school.dto.ClassRequestDTO;
import com.erp.school.dto.ClassResponseDTO;

public interface ClassServiceInt {
	
	ClassResponseDTO create(ClassRequestDTO dto);

    List<ClassResponseDTO> createBulk(List<ClassRequestDTO> list);

    ClassResponseDTO update(Long id, ClassRequestDTO dto);

    void softDelete(Long id);

    List<ClassResponseDTO> getActive();
    
    ClassResponseDTO toggleStatus(Long id);


}
