package com.tennisclub.reservations.service;

import com.tennisclub.reservations.dto.SurfaceDto;
import com.tennisclub.reservations.dto.create.SurfaceCreateDto;
import com.tennisclub.reservations.model.Surface;
import com.tennisclub.reservations.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class CrudServiceTest {

    @MockitoBean
    private CrudRepository<Surface> repository;

    @Autowired
    private CrudService<SurfaceDto, SurfaceCreateDto, SurfaceDto> service;
}
