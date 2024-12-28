package com.tennisclub.reservations.repository.impl;

import com.tennisclub.reservations.model.BaseEntity;
import com.tennisclub.reservations.repository.CrudRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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

        return entityManager.merge(entity);
    }

    @Override
    public Optional<T> findById(Long id) {
        log.debug("finding entity with id {}", id);

        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(type);
        var root = cq.from(type);

        cq.select(root).where(cb.equal(root.get("id"), id), cb.equal(root.get("deleted"), false));

        try {
            return Optional.of(entityManager.createQuery(cq).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        log.debug("finding all entities");

        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(type);
        var root = cq.from(type);

        cq.select(root).where(cb.equal(root.get("deleted"), false));

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
