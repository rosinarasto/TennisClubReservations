package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.config.ApiUris;
import com.tennisclub.reservations.dto.BaseDto;
import com.tennisclub.reservations.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericCrudController<TDto, TCreateDto, TUpdateDto extends BaseDto> {

    private final CrudService<TDto, TCreateDto, TUpdateDto> service;

    public GenericCrudController(CrudService<TDto, TCreateDto, TUpdateDto> service) {
        this.service = service;
    }

    @PostMapping(ApiUris.CREATE_URI)
    public ResponseEntity<TDto> createEntity(@RequestBody TCreateDto createDto) {
        try {
            var response = service.create(createDto);
            return ResponseEntity.ok().body(response);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(ApiUris.UPDATE_URI)
    public ResponseEntity<TDto> updateEntity(@RequestBody TUpdateDto updateDto, @PathVariable long id) {
        try {
            updateDto.setId(id);
            var response = service.update(updateDto);

            return response.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(ApiUris.DELETE_ALL_URI)
    public ResponseEntity<Void> deleteAllEntities() {
        service.softDeleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(ApiUris.DELETE_URI)
    public ResponseEntity<Void> deleteEntity(@PathVariable long id) {
        var entity = service.softDeleteById(id);

        if (entity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(ApiUris.GET_ALL_URI)
    public ResponseEntity<List<TDto>> getAllEntities() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(ApiUris.GET_URI)
    public ResponseEntity<TDto> getEntity(@PathVariable long id) {
        var entity = service.findById(id);
        return entity.map(surfaceDto -> ResponseEntity.ok().body(surfaceDto)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
