package com.erp.school.common;

import java.util.List;

public interface BaseService<E, R, D> {

    D create(R request);

    D getById(Long id);

    List<D> getAll();

    D update(Long id, R request);

    void delete(Long id);
    
     void validateBeforeCreate(R request);
     
    void beforeSave(E entity, R request);
}