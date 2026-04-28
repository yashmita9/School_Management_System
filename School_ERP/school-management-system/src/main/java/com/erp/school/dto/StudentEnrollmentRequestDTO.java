package com.erp.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Request DTO for Student Enrollment operations.
 * Used to assign student into class, section and academic year.
 * Also supports promotion and section transfer requests.
 * 
 * Contains student mapping details like roll number and current status.
 * 
 * @author Yashmita Rathore
 */
public class StudentEnrollmentRequestDTO {

	/**
	 * Unique ID of student.
	 */
	@NotNull(message = "Student ID is required")
	private Long studentId;

	/**
	 * Unique ID of class.
	 */
	@NotNull(message = "Class ID is required")
	private Long classId;

	/**
	 * Unique ID of section.
	 */
	@NotNull(message = "Section ID is required")
	private Long sectionId;

	private String rollNo;

	/**
	 * Current active enrollment status.
	 */
	private Boolean isCurrent;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

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

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
}