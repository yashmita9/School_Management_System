package com.erp.school.dto;

import java.time.LocalDate;

import com.erp.school.entityenum.Status;

public class AcademicYearResponseDTO {

    private Long id;
    private String yearName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean currentYear;
    private Status status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Boolean currentYear) {
        this.currentYear = currentYear;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}