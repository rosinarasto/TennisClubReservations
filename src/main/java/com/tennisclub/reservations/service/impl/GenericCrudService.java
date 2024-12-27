package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.mapper.GenericMapper;
import com.tennisclub.reservations.model.BaseEntity;
import com.tennisclub.reservations.repository.CrudRepository;
import com.tennisclub.reservations.service.CrudService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
public abstract class GenericCrudService<TModel extends BaseEntity, TDto, TCreateDto, TUpdateDto> implements CrudService<TDto, TCreateDto, TUpdateDto> {

    public final CrudRepository<TModel> repository;
    public final GenericMapper<TModel, TDto, TCreateDto, TUpdateDto> mapper;

    public final Class<TDto> dtoClass;
    public final Class<TModel> modelClass;

    public GenericCrudService(CrudRepository<TModel> repository,
                              GenericMapper<TModel, TDto, TCreateDto, TUpdateDto> mapper,
                              Class<TDto> dtoClass, Class<TModel> modelClass) {
        this.repository = repository;
        this.mapper = mapper;
        this.dtoClass = dtoClass;
        this.modelClass = modelClass;
    }

    @Override
    public TDto create(TCreateDto newEntity) {
        log.info("Creating new entity: {}", newEntity);

        var entity = mapper.toEntityFromSaveDto(newEntity);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public TDto update(TUpdateDto updateEntity) {
        log.info("Updating entity: {}", updateEntity);

        try {
            var entity = mapper.toEntityFromUpdateDto(updateEntity);
            return mapper.toDto(repository.update(entity));
        } catch (EntityNotFoundException ignored) {
//          TODO
            return null;
        }
    }

    @Override
    public Optional<TDto> findById(Long id) {
        log.info("Finding entity by id: {}", id);

        var entity = repository.findById(id);
        return entity.map(mapper::toDto);
    }

    @Override
    public List<TDto> findAll() {
        log.info("Finding all entities");

        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void softDeleteById(Long id) {
        log.info("Deleting entity by id: {}", id);

        repository.softDeleteById(id);
    }

    @Override
    public void softDeleteAll() {
        log.info("Deleting all entities");

        repository.softDeleteAll();
    }
}
