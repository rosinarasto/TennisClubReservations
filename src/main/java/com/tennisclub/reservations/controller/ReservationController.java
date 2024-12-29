package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.config.ApiUris;
import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.ReservationCreateDto;
import com.tennisclub.reservations.service.PriceCalculationService;
import com.tennisclub.reservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(ApiUris.RESERVATION_URI)
public class ReservationController {

    private final ReservationService reservationService;
    private final PriceCalculationService priceCalculationService;

    @Autowired
    public ReservationController(ReservationService service, PriceCalculationService priceCalculationService) {
        this.reservationService = service;
        this.priceCalculationService = priceCalculationService;
    }

    @PostMapping(ApiUris.CREATE_URI)
    public ResponseEntity<BigDecimal> createReservation(ReservationCreateDto reservationCreateDto) {
        var reservation = reservationService.create(reservationCreateDto);
        var price = priceCalculationService.calculatePrice(reservation);
        return ResponseEntity.ok().body(price);
    }

    @PutMapping(ApiUris.UPDATE_URI)
    public ResponseEntity<ReservationDto> updateEntity(@RequestBody ReservationDto updateDto, @PathVariable long id) {
        try {
            updateDto.setId(id);
            var response = reservationService.update(updateDto);

            return response.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(ApiUris.DELETE_ALL_URI)
    public ResponseEntity<Void> deleteAllEntities() {
        reservationService.softDeleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(ApiUris.DELETE_URI)
    public ResponseEntity<Void> deleteEntity(@PathVariable long id) {
        var entity = reservationService.softDeleteById(id);

        if (entity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(ApiUris.GET_ALL_URI)
    public ResponseEntity<List<ReservationDto>> getAllEntities() {
        return ResponseEntity.ok().body(reservationService.findAll());
    }

    @GetMapping(ApiUris.GET_URI)
    public ResponseEntity<ReservationDto> getEntity(@PathVariable long id) {
        var entity = reservationService.findById(id);
        return entity.map(surfaceDto -> ResponseEntity.ok().body(surfaceDto)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
