package com.example.trabalho.consulta.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class AbstractDao<T, ID extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(ID id) {
        entityManager.remove(entityManager.getReference(entityClass, id));
    }

    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
    }
}