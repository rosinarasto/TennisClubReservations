package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.SurfaceDto;
import com.tennisclub.reservations.dto.create.SurfaceCreateDto;
import com.tennisclub.reservations.mapper.SurfaceMapper;
import com.tennisclub.reservations.model.Surface;
import com.tennisclub.reservations.repository.SurfaceRepository;
import com.tennisclub.reservations.service.SurfaceService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
public class SurfaceServiceImpl extends GenericCrudService<Surface, SurfaceDto, SurfaceCreateDto, SurfaceDto> implements SurfaceService {

    private final SurfaceRepository surfaceRepository;

    private final SurfaceMapper surfaceMapper;

    @Autowired
    public SurfaceServiceImpl(SurfaceRepository surfaceRepository, SurfaceMapper mapper) {
        super(surfaceRepository, mapper, SurfaceDto.class, Surface.class);
        this.surfaceRepository = surfaceRepository;
        this.surfaceMapper = mapper;
    }

    @Override
    public SurfaceDto create(SurfaceCreateDto newEntity) {
        log.info("Creating new surface: {}", newEntity);

        var surface = surfaceRepository.findByName(newEntity.getName());

        if (surface.isPresent()) {
            log.info("Surface already exists");
            return surfaceMapper.toDto(surface.get());
        }

        var entity = surfaceMapper.toEntityFromCreateDto(newEntity);
        var savedEntity = surfaceRepository.save(entity);
        return surfaceMapper.toDto(savedEntity);
    }
}
