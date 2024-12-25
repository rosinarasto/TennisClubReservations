package com.tennisclub.reservations.repository.impl;

import com.tennisclub.reservations.model.BaseEntity;
import com.tennisclub.reservations.repository.CrudRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public class GenericCrudRepository<T extends BaseEntity> implements CrudRepository<T> {

    private final Class<T> type;

    @PersistenceContext
    private EntityManager entityManager;

    public GenericCrudRepository(Class<T> type) {
        this.type = type;
    }

    @Override
    @Transactional
    public T save(T newEntity) {
        if (newEntity.getId() == null) {
            entityManager.persist(newEntity);
            return newEntity;
        }

        return entityManager.merge(newEntity);
    }

    @Override
    @Transactional
    public void update(T entity) {
        var newEntity = findById(entity.getId());
        if (newEntity.isPresent() && !newEntity.get().isDeleted())
            entityManager.merge(entity);
        else
            throw new IllegalArgumentException("Court not found or is deleted");
    }

    @Override
    public Optional<T> findById(Long id) {
        var entity = entityManager.find(type, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("select e from " + type.getName() + " e", type).getResultList();
    }

    @Override
    @Transactional
    public void softDeleteById(Long id) {
        var entity = findById(id);
        if (entity.isPresent()) {
            entity.get().softDelete();
            save(entity.get());
        }
    }


    @Override
    @Transactional
    public void softDeleteAll() {
        var entities = findAll();

        for (var entity : entities) {
            entity.softDelete();
            save(entity);
        }

    }
}
