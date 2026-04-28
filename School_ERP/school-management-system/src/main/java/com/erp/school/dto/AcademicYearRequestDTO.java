package com.erp.school.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AcademicYearRequestDTO {

    @NotBlank(message = "Academic year name is required")
    @Pattern(
        regexp = "^\\d{4}-\\d{2}$",
        message = "Year format must be like 2025-26"
    )
    private String yearName;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    private Boolean currentYear = false;

    // ================= GETTERS / SETTERS =================

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
}