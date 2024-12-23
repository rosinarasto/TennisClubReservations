package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class UserCrudRepository implements CrudRepository<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void save(User newEntity) {
        entityManager.persist(newEntity);
    }

    @Override
    @Transactional
    public void update(User entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var entity = entityManager.find(User.class, id);
        if (entity != null)
            entityManager.remove(entity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("delete from User").executeUpdate();
    }
}
