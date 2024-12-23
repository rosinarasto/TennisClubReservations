package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.Surface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class SurfaceCrudRepository implements CrudRepository<Surface> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Surface> findAll() {
        return entityManager.createQuery("from Surface", Surface.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Surface newEntity) {
        entityManager.persist(newEntity);
    }

    @Override
    @Transactional
    public void update(Surface entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var entity = entityManager.find(Surface.class, id);
        if (entity != null)
            entityManager.remove(entity);
    }

    @Override
    public Optional<Surface> findById(Long id) {
        return Optional.of(entityManager.find(Surface.class, id));
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("delete from Surface").executeUpdate();
    }
}
