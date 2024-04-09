package com.HR.persistence.repo;

import com.HR.configuration.BusinessException;

import java.util.List;
import java.util.Optional;

public interface Repo<T> {
    T addEntity(T entity);

    Optional<T> getEntityById(Integer id);

    List<T> getAllEntities(int limit, int offset);

    void deleteEntityById(Integer id);

    T updateEntity(T entity) throws BusinessException;
}
