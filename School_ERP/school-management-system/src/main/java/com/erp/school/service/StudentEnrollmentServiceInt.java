package com.erp.school.service;

import java.util.List;

import com.erp.school.dto.StudentEnrollmentRequestDTO;
import com.erp.school.dto.StudentEnrollmentResponseDTO;

public interface StudentEnrollmentServiceInt {

    StudentEnrollmentResponseDTO assignStudent(StudentEnrollmentRequestDTO dto);

    StudentEnrollmentResponseDTO getCurrentEnrollment(Long studentId);

    List<StudentEnrollmentResponseDTO> getStudentHistory(Long studentId);

    List<StudentEnrollmentResponseDTO> getStudentsByClass(Long classId);

    List<StudentEnrollmentResponseDTO> getStudentsBySection(Long sectionId);

    StudentEnrollmentResponseDTO promoteStudent(StudentEnrollmentRequestDTO dto);

    StudentEnrollmentResponseDTO transferSection(Long enrollmentId, Long sectionId);
}