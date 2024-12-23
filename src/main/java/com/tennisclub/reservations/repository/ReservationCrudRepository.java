package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class ReservationCrudRepository implements CrudRepository<Reservation> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Reservation> findAll() {
        return entityManager.createQuery("select r from Reservation r", Reservation.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Reservation newEntity) {
        entityManager.persist(newEntity);
    }

    @Override
    @Transactional
    public void update(Reservation entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var entity = entityManager.find(Reservation.class, id);
        if (entity != null)
            entityManager.remove(entity);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.of(entityManager.find(Reservation.class, id));
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("delete from Reservation").executeUpdate();
    }
}
