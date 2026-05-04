package com.erp.school.repository;

import java.util.List;

import com.erp.school.common.BaseRepository;
import com.erp.school.entity.EnquiryEntity;
import com.erp.school.entityenum.EnquiryStatus;

public interface EnquiryRepository extends BaseRepository<EnquiryEntity, Long>{

	List<EnquiryEntity> findByStatus(EnquiryStatus status);

}
