package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.BaseEntity;

import java.util.List;
import java.util.Optional;

/**
 * Represents a repository for any entity.
 *
 * @param <T> the type of the entity.
 */
public interface CrudRepository<T extends BaseEntity> {

    /**
     * Find all entities.
     */
    List<T> findAll();

    /**
     * Persist given {@code newEntity}.
     */
    void save(T newEntity);

    /**
     * Update given {@code entity}.
     */
    void update(T entity);

    /**
     * Delete entity with given {@code id}.
     */
    void deleteById(Long id);

    /**
     * Find entity with given {@code id}.
     *
     * @return optional with found entity, or empty optional if no entity with given {@code id} is found
     */
    Optional<T> findById(Long id);

    /**
     * Delete all entities.
     */
    void deleteAll();
}
