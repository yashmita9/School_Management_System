package com.erp.school.dto;

import com.erp.school.entityenum.EnquiryStatus;

public class EnquiryResponseDTO {

	private Long id;
	private String studentName;
	private String mobile;
	private EnquiryStatus status;

	private String enquiryCode;

	public String getEnquiryCode() {
		return enquiryCode;
	}

	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public EnquiryStatus getStatus() {
		return status;
	}

	public void setStatus(EnquiryStatus status) {
		this.status = status;
	}

}
