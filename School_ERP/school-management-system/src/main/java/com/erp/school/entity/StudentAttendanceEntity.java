package com.erp.school.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.erp.school.entityenum.AttendanceStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

/**
 * Student Attendance Entity
 *
 * Stores daily student attendance marked by teacher.
 *
 * Flow:
 * Teacher Login -> Select Class/Section -> Mark Attendance ->
 * Save Present / Absent Records
 *
 * One student should have one record per date.
 *
 * @author Yashmita Rathore
 */
@Entity
@Table(name = "student_attendance")
public class StudentAttendanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Student whose attendance is marked
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private StudentEntity student;

	/**
	 * Class
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id", nullable = false)
	private ClassEntity classEntity;

	/**
	 * Section
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id", nullable = false)
	private SectionEntity sectionEntity;

	/**
	 * Attendance Date
	 */
	private LocalDate attendanceDate;

	/**
	 * PRESENT / ABSENT
	 */
	@Enumerated(EnumType.STRING)
	private AttendanceStatus status;

	/**
	 * Teacher who marked attendance
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marked_by", nullable = false)
	private UserEntity markedBy;

	/**
	 * Record created time
	 */
	private LocalDateTime createdAt;

	/**
	 * Record updated time
	 */
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
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

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public AttendanceStatus getStatus() {
		return status;
	}

	public void setStatus(AttendanceStatus status) {
		this.status = status;
	}

	public UserEntity getMarkedBy() {
		return markedBy;
	}

	public void setMarkedBy(UserEntity markedBy) {
		this.markedBy = markedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}