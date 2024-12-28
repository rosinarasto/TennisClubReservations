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
     * Persist given {@code newEntity}.
     *
     * @return the persisted entity with generated id.
     */
    T save(T newEntity);


    /**
     * Update given {@code entity}.
     *
     * @return the updated entity.
     */
    T update(T entity);

    /**
     * Find entity with given {@code id}.
     *
     * @return optional with found entity, or empty optional if no entity with given {@code id} is found
     */
    Optional<T> findById(Long id);

    /**
     * Find all entities.
     */
    List<T> findAll();

    /**
     * Soft delete entity with given {@code id}.
     */
    void softDeleteById(Long id);

    /**
     * Soft delete all entities.
     */
    void softDeleteAll();
}
