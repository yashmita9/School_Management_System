package com.erp.school.common;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.erp.school.entityenum.Status;
import com.erp.school.exception.BadRequestException;

public abstract class BaseServiceImpl<E, R, D> implements BaseService<E, R, D> {

	protected final BaseRepository<E, Long> repository;
	protected final ModelMapper modelMapper;

	public BaseServiceImpl(BaseRepository<E, Long> repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	// 🔥 MUST be implemented by child service
	protected abstract Class<E> getEntityClass();

	protected abstract Class<D> getDtoClass();

	@Override
	public D create(R request) {

		// 🔥 validation hook
		validateBeforeCreate(request);

		E entity = modelMapper.map(request, getEntityClass());
		beforeSave(entity, request);
		E saved = repository.save(entity);
		return modelMapper.map(saved, getDtoClass());
	}

	@Override
	public D getById(Long id) {

		E entity = repository.findById(id).orElseThrow(() -> new BadRequestException("Record not found"));

		return modelMapper.map(entity, getDtoClass());
	}

	@Override
	public List<D> getAll() {
		return repository.findAll().stream().map(e -> modelMapper.map(e, getDtoClass())).collect(Collectors.toList());
	}


	@Override
	public void delete(Long id) {

		E entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Record not found with id: " + id));

		repository.delete(entity);
	}

}