package com.erp.school.entity;

import com.erp.school.common.BaseEntity;
import com.erp.school.entityenum.StudentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_enrollment")
public class StudentEnrollmentEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentEntity student;

	@ManyToOne
	@JoinColumn(name = "academic_year_id")
	private AcademicYearEntity academicYear;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity classEntity;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private SectionEntity sectionEntity;

	@Column(name = "roll_no")
	private String rollNo;

	@Column(name = "is_current")
	private Boolean isCurrent;

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public AcademicYearEntity getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYearEntity academicYear) {
		this.academicYear = academicYear;
	}

	public ClassEntity getClassEntity() {
		return classEntity;
	}

	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}

	public SectionEntity getSectionEntity() {
		return sectionEntity;
	}

	public void setSectionEntity(SectionEntity sectionEntity) {
		this.sectionEntity = sectionEntity;
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