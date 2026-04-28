package com.erp.school.dto;

public class StudentEnrollmentResponseDTO {

	private Long id;

	private Long studentId;
	private String studentName;

	private Long academicYearId;
	private String academicYearName;

	private Long classId;
	private String className;

	private Long sectionId;
	private String sectionName;

	private String rollNo;
	private Boolean isCurrent;

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

	public Long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public String getAcademicYearName() {
		return academicYearName;
	}

	public void setAcademicYearName(String academicYearName) {
		this.academicYearName = academicYearName;
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