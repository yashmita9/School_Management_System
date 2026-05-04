package com.erp.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.erp.school.common.BaseRepository;
import com.erp.school.entity.StudentEnrollmentEntity;

public interface StudentEnrollmentRepository
        extends BaseRepository<StudentEnrollmentEntity, Long> {

    // Current enrollment of student
    Optional<StudentEnrollmentEntity> findByStudentIdAndIsCurrentTrue(Long studentId);

    // All academic history of one student
    List<StudentEnrollmentEntity> findByStudentIdOrderByIdDesc(Long studentId);

    // Search by academic year
    List<StudentEnrollmentEntity> findByAcademicYearId(Long academicYearId);

    // Search by class
    List<StudentEnrollmentEntity> findByClassEntityId(Long classId);

    // Search by section
    List<StudentEnrollmentEntity> findBySectionEntityId(Long sectionId);

    // Class + Section wise students
    List<StudentEnrollmentEntity> findByClassEntityIdAndSectionEntityId(
            Long classId, Long sectionId);

    // Academic Year + Class wise students
    List<StudentEnrollmentEntity> findByAcademicYearIdAndClassEntityId(
            Long academicYearId, Long classId);

    // Roll number in same session/class
    Optional<StudentEnrollmentEntity>
        findByAcademicYearIdAndClassEntityIdAndRollNo(
            Long academicYearId,
            Long classId,
            String rollNo
        );

    // Current students list
    List<StudentEnrollmentEntity> findByIsCurrentTrue();

	boolean existsByStudentIdAndAcademicYearId(Long studentId, Long id);
	
	@Query("""
			SELECT COALESCE(MAX(CAST(e.rollNo as integer)),0)
			FROM StudentEnrollmentEntity e
			WHERE e.academicYear.id = :yearId
			AND e.classEntity.id = :classId
			AND e.sectionEntity.id = :sectionId
			""")
			Integer findMaxRollNo(Long yearId, Long classId, Long sectionId);
}