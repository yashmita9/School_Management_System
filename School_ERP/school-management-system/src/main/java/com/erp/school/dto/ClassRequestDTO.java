package com.erp.school.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class ClassRequestDTO {

	@NotBlank(message = "Class name required")
	private String className;

	private String description;

	private Integer displayOrder;

	private List<SectionRequestDTO> sections;

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<SectionRequestDTO> getSections() {
		return sections;
	}

	public void setSections(List<SectionRequestDTO> sections) {
		this.sections = sections;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
