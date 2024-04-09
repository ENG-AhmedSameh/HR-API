package com.HR.persistence.repo;

import jakarta.persistence.EntityManager;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
public abstract class RepoImpl<T> {
    protected EntityManager entityManager;
    protected Class<T> table;
    protected RepoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        this.table = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    // findOne
    public Optional<T> findOne(int id){
        return Optional.ofNullable(entityManager.find(table, id));
    }

    // findAll
    public List<T> findAll(int page, int size){
        return entityManager.createQuery("from " + table.getName() + " u", table)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
    // create
    public void create(T entity){
        entityManager.persist(entity);
    }
    // deleteById
    public void delete(T entity){
        entityManager.remove(entity);
    }
    // update
    public T update(T entity){
        entityManager.merge(entity);
        return entity;
    }



    // Passing a JPQL
    public List<Object> findByNamedQuery(String queryName) {
        return entityManager.createQuery(queryName).getResultList();
    }
}
