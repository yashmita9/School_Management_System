package com.erp.school.dto;

import java.time.LocalDate;

/**
 * Response DTO for attendance data.
 *
 * @author Yashmita Rathore
 */
public class StudentAttendanceResponseDTO {

	private Long id;
	private Long studentId;
	private String studentName;

	private Long classId;
	private String className;

	private Long sectionId;
	private String sectionName;

	private LocalDate attendanceDate;
	private String status;

	private Long markedById;
	private String markedByName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getMarkedById() {
		return markedById;
	}

	public void setMarkedById(Long markedById) {
		this.markedById = markedById;
	}

	public String getMarkedByName() {
		return markedByName;
	}

	public void setMarkedByName(String markedByName) {
		this.markedByName = markedByName;
	}
}