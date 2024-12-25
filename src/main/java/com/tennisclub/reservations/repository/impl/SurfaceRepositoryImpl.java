package com.tennisclub.reservations.repository.impl;

import com.tennisclub.reservations.model.Surface;
import com.tennisclub.reservations.repository.SurfaceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SurfaceRepositoryImpl extends GenericCrudRepository<Surface> implements SurfaceRepository {

    public SurfaceRepositoryImpl() {
        super(Surface.class);
    }
}
