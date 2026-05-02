package com.erp.school.repository;

import java.util.List;
import java.util.Optional;

import com.erp.school.common.BaseRepository;
import com.erp.school.entity.CategoryEntity;
import com.erp.school.entityenum.Status;

public interface CategoryRepository extends BaseRepository<CategoryEntity, Long>{
	
	Optional<CategoryEntity> findByNameIgnoreCase(String name);
	
	Optional<CategoryEntity> findByCodeIgnoreCase(String code);
	
	boolean existsByNameIgnoreCase(String name);
	
	boolean existsByCodeIgnoreCase(String code);

	boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);

	boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
	
	List<CategoryEntity> findByStatusOrderByNameAsc(Status status);
	

}
