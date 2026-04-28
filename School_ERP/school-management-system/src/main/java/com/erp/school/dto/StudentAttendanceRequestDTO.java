package com.erp.school.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for marking class attendance.
 *
 * Teacher selects class + section + date then sends all student attendance
 * records.
 *
 * @author Yashmita Rathore
 */
public class StudentAttendanceRequestDTO {

	@NotNull(message = "Class ID is required")
	private Long classId;

	@NotNull(message = "Section ID is required")
	private Long sectionId;

	@NotNull(message = "Attendance date is required")
	private LocalDate attendanceDate;

	@NotEmpty(message = "Attendance records are required")
	@Valid
	private List<StudentAttendanceRecordDTO> records;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public List<StudentAttendanceRecordDTO> getRecords() {
		return records;
	}

	public void setRecords(List<StudentAttendanceRecordDTO> records) {
		this.records = records;
	}
}