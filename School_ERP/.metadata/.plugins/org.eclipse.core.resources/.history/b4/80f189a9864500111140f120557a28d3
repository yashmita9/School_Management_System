package com.erp.school.dto;

import java.time.LocalDate;

import com.erp.school.entityenum.EnquiryStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EnquiryRequestDTO {

    @NotBlank(message = "Student name is required")
    @Size(min = 2, max = 50, message = "Student name must be between 2 to 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Student name must contain only letters")
    private String studentName;

    @NotBlank(message = "Parents name is required")
    @Size(min = 2, max = 50, message = "Parents name must be between 2 to 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Parents name must contain only letters")
    private String parentsName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Enter valid 10 digit mobile number")
    private String mobile;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid email address")
    private String email;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 200, message = "Address must be between 5 to 200 characters")
    private String address;

    @NotBlank(message = "Class interested is required")
    private String classInterested;

    @NotNull(message = "Status is required")
    private EnquiryStatus status;

    @NotBlank(message = "Source is required")
    @Pattern(
        regexp = "^(WALK-IN|CALL|WEBSITE|REFERENCE|SOCIAL_MEDIA)$",
        message = "Source must be WALK-IN, CALL, WEBSITE, REFERENCE or SOCIAL_MEDIA"
    )
    private String source;

    @FutureOrPresent(message = "Follow up date cannot be past date")
    private LocalDate followUpDate;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
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