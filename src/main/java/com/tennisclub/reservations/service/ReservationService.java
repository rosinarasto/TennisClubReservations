package com.tennisclub.reservations.service;

import com.tennisclub.reservations.model.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();
    Reservation save(Reservation reservation);
}
