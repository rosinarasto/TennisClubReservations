package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.Court;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourtRepository implements CrudRepository<Court> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Court> findAll() {
        return entityManager.createQuery("from Court", Court.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Court newEntity) {
        entityManager.persist(newEntity);
    }

    @Override
    @Transactional
    public void update(Court entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var entity = entityManager.find(Court.class, id);
        if (entity != null)
            entityManager.remove(entity);
    }

    @Override
    public Optional<Court> findById(Long id) {
        return Optional.of(entityManager.find(Court.class, id));
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("delete from Court").executeUpdate();
    }
}
