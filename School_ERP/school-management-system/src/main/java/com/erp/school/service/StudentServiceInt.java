package com.erp.school.service;

import java.util.List;

import com.erp.school.dto.StudentRequestDTO;
import com.erp.school.dto.StudentResponseDTO;

public interface StudentServiceInt {

	StudentResponseDTO createStudent(StudentRequestDTO dto);

	StudentResponseDTO getStudentById(Long id);

	List<StudentResponseDTO> getAllStudents();

	StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);

	void deleteStudent(Long id);

	List<StudentResponseDTO> searchByName(String name);

	StudentResponseDTO convertEnquiryToStudent(Long enquiryId);

}
