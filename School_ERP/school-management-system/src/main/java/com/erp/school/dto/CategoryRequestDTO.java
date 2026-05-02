package com.erp.school.dto;

import com.erp.school.entityenum.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {

	@NotBlank(message = "Name is required")
	@Size(max = 50, message = "Name must be maximum 50 characters")
	private String name;

	@NotBlank(message = "Code is required")
	@Size(max = 10, message = "Code must be maximum 10 characters")
	private String code;

	@Size(max = 255, message = "Description must be maximum 255 characters")
	private String description;

	@NotNull(message = "Status is required")
	private Status status;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
