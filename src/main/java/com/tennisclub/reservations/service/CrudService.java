package com.tennisclub.reservations.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Represents a service for any entity.
 *
 * @param <TDto> universal dto of the entity.
 * @param <TCreateDto> save dto of the entity
 * @param <TUpdateDto> update dto of the entity
 */
public interface CrudService<TDto, TCreateDto, TUpdateDto> {

    /**
     * Create given {@code newEntity}.
     *
     * @return the created entity with generated id.
     */
    TDto create(TCreateDto newEntity);


    /**
     * Update given {@code entity}.
     *
     * @return optional with updated entity, or empty optional if entity was not found
     */
    Optional<TDto> update(TUpdateDto entity);

    /**
     * Find entity with given {@code id}.
     *
     * @return optional with found entity, or empty optional if no entity with given {@code id} is found
     */
    Optional<TDto> findById(Long id);

    /**
     * Find all entities.
     */
    Page<TDto> findAll(Pageable pageable);

    /**
     * Soft delete entity with given {@code id}.
     *
     * @return the deleted entity with id.
     */
    Optional<TDto> softDeleteById(Long id);

    /**
     * Soft delete all entities.
     */
    void softDeleteAll(Pageable pageable);
}
