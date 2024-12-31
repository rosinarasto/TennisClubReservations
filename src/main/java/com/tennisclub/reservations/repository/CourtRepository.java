package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.Court;

import java.util.Optional;

public interface CourtRepository extends CrudRepository<Court> {

    Optional<Court> findByCourtNumber(int number);
}
