package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.config.ApiUris;
import com.tennisclub.reservations.dto.SurfaceDto;
import com.tennisclub.reservations.dto.create.SurfaceCreateDto;
import com.tennisclub.reservations.service.SurfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUris.SURFACE_URI)
public class SurfaceController extends GenericCrudController<SurfaceDto, SurfaceCreateDto, SurfaceDto> {

    @Autowired
    public SurfaceController(SurfaceService surfaceService) {
        super(surfaceService);
    }
}
