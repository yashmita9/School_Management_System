package com.erp.school.dto;

import java.util.List;

public class ClassResponseDTO {

	private Long id;
	private String className;
	private String description;
	private String status;

	private List<SectionResponseDTO> sections;

	public List<SectionResponseDTO> getSections() {
		return sections;
	}

	public void setSections(List<SectionResponseDTO> sections) {
		this.sections = sections;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
