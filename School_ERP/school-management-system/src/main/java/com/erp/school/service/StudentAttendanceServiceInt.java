package com.erp.school.service;

import java.time.LocalDate;
import java.util.List;

import com.erp.school.dto.StudentAttendanceRequestDTO;
import com.erp.school.dto.StudentAttendanceResponseDTO;

/**
 * Service Interface for Student Attendance Module.
 *
 * Features:
 * - Mark daily attendance
 * - View class attendance by date
 * - View student attendance history
 * - View monthly attendance
 * - Update same day attendance
 *
 * Flow:
 * Teacher Login -> Select Class -> Mark Attendance -> Save
 *
 * @author Yashmita Rathore
 */
public interface StudentAttendanceServiceInt {

	/**
	 * Mark daily attendance for class students
	 *
	 * @param dto attendance request
	 * @return success message
	 */
	String markAttendance(StudentAttendanceRequestDTO dto);

	/**
	 * Get class attendance by class, section and date
	 *
	 * @param classId class id
	 * @param sectionId section id
	 * @param attendanceDate date
	 * @return attendance list
	 */
	List<StudentAttendanceResponseDTO> getClassAttendance(
			Long classId,
			Long sectionId,
			LocalDate attendanceDate);

	/**
	 * Full attendance history of one student
	 *
	 * @param studentId student id
	 * @return attendance list
	 */
	List<StudentAttendanceResponseDTO> getStudentAttendanceHistory(Long studentId);

	/**
	 * Monthly attendance report of student
	 *
	 * @param studentId student id
	 * @param startDate from date
	 * @param endDate to date
	 * @return attendance list
	 */
	List<StudentAttendanceResponseDTO> getStudentAttendanceByDateRange(
			Long studentId,
			LocalDate startDate,
			LocalDate endDate);
}