package com.erp.school.repository;

import java.util.List;
import java.util.Optional;

import com.erp.school.common.BaseRepository;
import com.erp.school.entity.EnquiryEntity;
import com.erp.school.entity.StudentEntity;
import com.erp.school.entityenum.EnquiryStatus;

public interface EnquiryRepository extends BaseRepository<EnquiryEntity, Long>{

	List<EnquiryEntity> findByStatus(EnquiryStatus status);

	Optional<EnquiryEntity> findByEnquiryCode(String code);

}
