package com.erp.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request DTO for Subject module. Used to create and update subject.
 * 
 * @author Yashmita Rathore
 */
public class SubjectRequestDTO {

	@NotBlank(message = "Subject code is required")
	@Size(max = 20, message = "Subject code must not exceed 20 characters")
	private String subjectCode;

	@NotBlank(message = "Subject name is required")
	@Size(max = 100, message = "Subject name must not exceed 100 characters")
	private String subjectName;

	@Size(max = 255, message = "Description must not exceed 255 characters")
	private String description;

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
}