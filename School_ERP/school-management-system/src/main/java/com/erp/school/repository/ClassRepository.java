package com.erp.school.repository;

import java.util.List;

import com.erp.school.common.BaseRepository;
import com.erp.school.entity.ClassEntity;
import com.erp.school.entityenum.Status;

public interface ClassRepository extends BaseRepository<ClassEntity, Long> {

	boolean existsByClassName(String className);

	List<ClassEntity> findByStatus(Status status);

}
