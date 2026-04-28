package com.erp.school.entity;

import com.erp.school.common.BaseEntity;
import com.erp.school.entityenum.EnquiryStatus;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enquiry")
public class EnquiryEntity extends BaseEntity {

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "parents_name")
	private String parentsName;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "class_interested")
	private String classInterested;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private EnquiryStatus status;

	@Column(name = "source")
	private String source; // WALK-IN, CALL, WEBSITE

	@Column(name = "follow_up_date")
	private LocalDate followUpDate;

	@Column(name = "remarks", length = 1000)
	private String remarks;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getParentsName() {
		return parentsName;
	}

	public void setParentsName(String parentsName) {
		this.parentsName = parentsName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClassInterested() {
		return classInterested;
	}

	public void setClassInterested(String classInterested) {
		this.classInterested = classInterested;
	}

	public EnquiryStatus getStatus() {
		return status;
	}

	public void setStatus(EnquiryStatus status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public LocalDate getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(LocalDate followUpDate) {
		this.followUpDate = followUpDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}