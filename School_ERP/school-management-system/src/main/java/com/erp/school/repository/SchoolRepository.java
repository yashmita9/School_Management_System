package com.erp.school.repository;

import java.util.Optional;

import com.erp.school.common.BaseRepository;
import com.erp.school.entity.SchoolEntity;
import com.erp.school.entityenum.Status;

public interface SchoolRepository extends BaseRepository<SchoolEntity, Long> {

	boolean existsByStatus(Status status);

	Optional<SchoolEntity> findByStatus(Status status);

}
