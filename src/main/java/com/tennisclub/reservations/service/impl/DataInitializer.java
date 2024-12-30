package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.SurfaceDto;
import com.tennisclub.reservations.dto.create.CourtCreateDto;
import com.tennisclub.reservations.dto.create.SurfaceCreateDto;
import com.tennisclub.reservations.exception.ResourceAlreadyExistsException;
import com.tennisclub.reservations.service.CourtService;
import com.tennisclub.reservations.service.SurfaceService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DataInitializer {

    private final CourtService courtService;
    private final SurfaceService surfaceService;

    private final List<SurfaceDto> surfaces = new ArrayList<>();

    @Value("${data.init.enabled:false}")
    private boolean dataInitEnabled;

    @Autowired
    public DataInitializer(CourtService courtService, SurfaceService surfaceService) {
        this.courtService = courtService;
        this.surfaceService = surfaceService;
    }

    @PostConstruct
    public void initData() {
        if (!dataInitEnabled)
            return;

        initSurfaces();
        initCourts();
    }

    private void initSurfaces() {
        try {
            surfaces.add(surfaceService.create(new SurfaceCreateDto(BigDecimal.valueOf(0.24), "Hard")));
        } catch (ResourceAlreadyExistsException ignored) {}
        try {
            surfaces.add(surfaceService.create(new SurfaceCreateDto(BigDecimal.valueOf(0.36), "Clay")));
        } catch (ResourceAlreadyExistsException ignored) {}
    }

    private void initCourts() {
        try {
            courtService.create(new CourtCreateDto("Emerald Bay Tennis Center", 1, surfaces.get(0)));
        } catch (ResourceAlreadyExistsException ignored) {}
        try {
            courtService.create(new CourtCreateDto("Riverside Racket Club", 2, surfaces.get(1)));
        } catch (ResourceAlreadyExistsException ignored) {}
        try {
            courtService.create(new CourtCreateDto("Sunset Point Tennis Courts", 3, surfaces.get(1)));
        } catch (ResourceAlreadyExistsException ignored) {}
        try {
            courtService.create(new CourtCreateDto("Grandview Athletic Park", 4, surfaces.get(0)));
        } catch (ResourceAlreadyExistsException ignored) {}
    }
}
