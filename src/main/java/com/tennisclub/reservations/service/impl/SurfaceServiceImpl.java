package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.SurfaceDto;
import com.tennisclub.reservations.dto.create.SurfaceCreateDto;
import com.tennisclub.reservations.dto.update.SurfaceUpdateDto;
import com.tennisclub.reservations.mapper.SurfaceMapper;
import com.tennisclub.reservations.model.Surface;
import com.tennisclub.reservations.repository.SurfaceRepository;
import com.tennisclub.reservations.service.SurfaceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SurfaceServiceImpl extends GenericCrudService<Surface, SurfaceDto, SurfaceCreateDto, SurfaceUpdateDto> implements SurfaceService {

    @Autowired
    public SurfaceServiceImpl(SurfaceRepository surfaceRepository, SurfaceMapper mapper) {
        super(surfaceRepository, mapper, SurfaceDto.class, Surface.class);
    }
}
