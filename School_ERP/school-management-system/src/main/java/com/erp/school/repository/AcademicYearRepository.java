package com.erp.school.repository;

import java.util.List;
import java.util.Optional;

import com.erp.school.common.BaseRepository;
import com.erp.school.entity.AcademicYearEntity;
import com.erp.school.entityenum.Status;

public interface AcademicYearRepository extends BaseRepository<AcademicYearEntity, Long> {

	boolean existsByYearName(String yearName);

	List<AcademicYearEntity> findByStatus(Status status);

	Optional<AcademicYearEntity> findByCurrentYearTrue();

	boolean existsByCurrentYearTrue();

	Optional<AcademicYearEntity> findByIdAndStatus(Long id, Status status);

}
