package com.erp.school.dto;

import com.erp.school.entityenum.Status;

/**
 * Response DTO for Subject module.
 * 
 * @author Yashmita Rathore
 */
public class SubjectResponseDTO {

	private Long id;
	private String subjectCode;
	private String subjectName;
	private String description;
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}