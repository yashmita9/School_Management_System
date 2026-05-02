package com.erp.school.entity;

import java.util.ArrayList;
import java.util.List;

import com.erp.school.common.BaseEntity;
import com.erp.school.entityenum.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Classes")
public class ClassEntity extends BaseEntity {

	@Column(name = "class_name")
	private String className;

	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SectionEntity> sections = new ArrayList<>();

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<SectionEntity> getSections() {
		return sections;
	}

	public void setSections(List<SectionEntity> sections) {
		this.sections = sections;
	}

}
