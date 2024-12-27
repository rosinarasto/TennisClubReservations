package com.tennisclub.reservations.repository.impl;

import com.tennisclub.reservations.model.BaseEntity;
import com.tennisclub.reservations.repository.CrudRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
@Slf4j
public abstract class GenericCrudRepository<T extends BaseEntity> implements CrudRepository<T> {

    private final Class<T> type;

    @PersistenceContext
    private EntityManager entityManager;

    public GenericCrudRepository(Class<T> type) {
        this.type = type;
    }

    @Override
    @Transactional
    public T save(T newEntity) {
        log.debug("saving entity {}", newEntity);

        if (newEntity.getId() == null) {
            entityManager.persist(newEntity);
            return newEntity;
        }

        return entityManager.merge(newEntity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        log.debug("updating entity {}", entity);

        var newEntity = findById(entity.getId());
        if (newEntity.isEmpty())
            throw new EntityNotFoundException("Entity not found or is deleted");

        return entityManager.merge(entity);
    }

    @Override
    public Optional<T> findById(Long id) {
        log.debug("finding entity with id {}", id);

        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(type);
        var root = cq.from(type);

        var idPredicate = cb.equal(root.get("id"), id);
        var deletedPredicate = cb.equal(root.get("deleted"), false);

        cq.select(root).where(idPredicate).where(deletedPredicate);

        return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public List<T> findAll() {
        log.debug("finding all entities");

        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(type);
        var root = cq.from(type);

        var deletedPredicate = cb.equal(root.get("deleted"), false);

        cq.select(root).where(deletedPredicate);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    @Transactional
    public void softDeleteById(Long id) {
        log.debug("deleting entity with id {}", id);

        var entity = findById(id);
        if (entity.isPresent()) {
            entity.get().softDelete();
            save(entity.get());
        }
    }


    @Override
    @Transactional
    public void softDeleteAll() {
        log.debug("deleting all entities");

        var entities = findAll();

        for (var entity : entities) {
            entity.softDelete();
            save(entity);
        }

    }
}
