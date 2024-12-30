package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.Surface;

import java.util.Optional;

public interface SurfaceRepository extends CrudRepository<Surface> {

    Optional<Surface> findByName(String name);
}
