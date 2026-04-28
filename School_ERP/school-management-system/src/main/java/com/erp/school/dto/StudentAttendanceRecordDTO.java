package com.erp.school.dto;

import com.erp.school.entityenum.AttendanceStatus;

import jakarta.validation.constraints.NotNull;

/**
 * Single student attendance row.
 *
 * Example:
 * Student Rahul = PRESENT
 *
 * @author Yashmita Rathore
 */
public class StudentAttendanceRecordDTO {

	@NotNull(message = "Student ID is required")
	private Long studentId;

	@NotNull(message = "Attendance status is required")
	private AttendanceStatus status;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public AttendanceStatus getStatus() {
		return status;
	}

	public void setStatus(AttendanceStatus status) {
		this.status = status;
	}
}