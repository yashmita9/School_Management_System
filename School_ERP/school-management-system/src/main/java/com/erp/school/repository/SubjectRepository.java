package com.erp.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.school.common.BaseRepository;
import com.erp.school.entity.SubjectEntity;
import com.erp.school.entityenum.Status;

/**
 * Repository interface for Subject module. Handles database operations for
 * subject master.
 * 
 * @author Yashmita Rathore
 */
@Repository
public interface SubjectRepository extends BaseRepository<SubjectEntity, Long> {

	/**
	 * Find subject by subject code.
	 *
	 * @param subjectCode subject code
	 * @return subject entity
	 */
	Optional<SubjectEntity> findBySubjectCode(String subjectCode);

	/**
	 * Check if subject code already exists.
	 *
	 * @param subjectCode subject code
	 * @return true if exists
	 */
	boolean existsBySubjectCode(String subjectCode);

	/**
	 * Check if subject name already exists.
	 *
	 * @param subjectName subject name
	 * @return true if exists
	 */
	boolean existsBySubjectNameIgnoreCase(String subjectName);

	/**
	 * Find all active subjects.
	 *
	 * @param status status
	 * @return list of subjects
	 */
	List<SubjectEntity> findByStatus(Status status);

	/**
	 * Search subjects by name.
	 *
	 * @param keyword subject name keyword
	 * @return list of subjects
	 */
	List<SubjectEntity> findBySubjectNameContainingIgnoreCase(String keyword);
}