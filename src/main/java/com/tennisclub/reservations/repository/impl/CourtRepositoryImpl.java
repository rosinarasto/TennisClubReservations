package com.tennisclub.reservations.repository.impl;

import com.tennisclub.reservations.model.Court;
import com.tennisclub.reservations.repository.CourtRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CourtRepositoryImpl extends GenericCrudRepository<Court> implements CourtRepository {

    public CourtRepositoryImpl() {
        super(Court.class);
    }
}
