package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.model.Reservation;
import com.tennisclub.reservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.findAll();
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }
}
