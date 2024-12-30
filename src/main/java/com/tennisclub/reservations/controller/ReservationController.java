package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.config.ApiUris;
import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.ReservationCreateDto;
import com.tennisclub.reservations.service.ReservationService;
import com.tennisclub.reservations.util.PriceCalculationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(ApiUris.RESERVATION_URI)
public class ReservationController extends GenericCrudController<ReservationDto, ReservationCreateDto, ReservationDto> {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService service) {
        super(service);
        this.reservationService = service;
    }

    @Override
    public ResponseEntity<BigDecimal> createEntity(ReservationCreateDto reservationCreateDto) {
        try {
            var reservation = reservationService.create(reservationCreateDto);
            var price = PriceCalculationUtil.calculatePrice(reservation);
            return ResponseEntity.ok().body(price);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
