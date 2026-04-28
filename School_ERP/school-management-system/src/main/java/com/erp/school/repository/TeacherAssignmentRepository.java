package com.erp.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.school.entity.TeacherAssignmentEntity;
import com.erp.school.entity.UserEntity;
import com.erp.school.entityenum.Status;

/**
 * Repository for Teacher Assignment module. Handles
 * teacher-class-section-subject mapping operations.
 * 
 * @author Yashmita Rathore
 */
@Repository
public interface TeacherAssignmentRepository extends JpaRepository<TeacherAssignmentEntity, Long> {

	/**
	 * Duplicate check: Same teacher + class + section + subject
	 */
	boolean existsByTeacherIdAndClassEntityIdAndSectionEntityIdAndSubjectId(Long teacherId, Long classId,
			Long sectionId, Long subjectId);

	/**
	 * Get all assignments by teacher.
	 */
	List<TeacherAssignmentEntity> findByTeacherId(Long teacherId);

	/**
	 * Get all assignments by class.
	 */
	List<TeacherAssignmentEntity> findByClassEntityId(Long classId);

	/**
	 * Get all assignments bySection.
	 */
	List<TeacherAssignmentEntity> findBySectionEntityId(Long sectionId);

	/**
	 * Get all assignments by subject.
	 */
	List<TeacherAssignmentEntity> findBySubjectId(Long subjectId);

	/**
	 * Get all active assignments.
	 */
	List<TeacherAssignmentEntity> findByStatus(Status status);

	boolean existsByTeacherIdAndClassEntityIdAndSectionEntityIdAndSubjectIdAndIdNot(Long teacherId, Long classId,
			Long sectionId, Long subjectId, Long id);

	List<TeacherAssignmentEntity> findByClassEntityIdAndSectionEntityId(Long classId, Long sectionId);
}