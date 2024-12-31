package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.config.ApiUris;
import com.tennisclub.reservations.dto.BaseDto;
import com.tennisclub.reservations.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class GenericCrudController<TDto, TCreateDto, TUpdateDto extends BaseDto> {

    private final CrudService<TDto, TCreateDto, TUpdateDto> service;

    public GenericCrudController(CrudService<TDto, TCreateDto, TUpdateDto> service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> createEntity(@Valid @RequestBody TCreateDto createDto) {
        var response = service.create(createDto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping()
    public ResponseEntity<TDto> updateEntity(@Valid @RequestBody TUpdateDto updateDto) {
        var response = service.update(updateDto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAllEntities(Pageable pageable) {
        service.softDeleteAll(pageable);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(ApiUris.ID_URI)
    public ResponseEntity<TDto> deleteEntity(@PathVariable long id) {
        var entity = service.softDeleteById(id);
        return entity.map(surfaceDto -> ResponseEntity.ok().body(surfaceDto)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping()
    public ResponseEntity<Page<TDto>> getAllEntities(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(ApiUris.ID_URI)
    public ResponseEntity<TDto> getEntity(@PathVariable long id) {
        var entity = service.findById(id);
        return entity.map(surfaceDto -> ResponseEntity.ok().body(surfaceDto)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
