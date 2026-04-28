package com.erp.school.dto;

import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for Teacher Assignment module. Used to assign teacher with class,
 * section and subject.
 * 
 * @author Yashmita Rathore
 */
public class TeacherAssignmentRequestDTO {

	@NotNull(message = "Teacher ID is required")
	private Long teacherId;

	@NotNull(message = "Class ID is required")
	private Long classId;

	@NotNull(message = "Section ID is required")
	private Long sectionId;

	@NotNull(message = "Subject ID is required")
	private Long subjectId;

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
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

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
}