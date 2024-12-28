package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.config.ApiUris;
import com.tennisclub.reservations.dto.CourtDto;
import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.CourtCreateDto;
import com.tennisclub.reservations.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiUris.COURT_URI)
public class CourtController extends GenericCrudController<CourtDto, CourtCreateDto, CourtDto> {

    private final CourtService courtService;

    @Autowired
    public CourtController(CourtService service) {
        super(service);
        this.courtService = service;
    }

    @GetMapping(ApiUris.COURT_RESERVATIONS_URI)
    public ResponseEntity<List<ReservationDto>> getCourtReservations(@PathVariable int number) {
        try {
            var reservations = courtService.findReservations(number);
            return ResponseEntity.ok().body(reservations);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
