package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.config.ApiUris;
import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.ReservationCreateDto;
import com.tennisclub.reservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUris.RESERVATION_URI)
public class ReservationController extends GenericCrudController<ReservationDto, ReservationCreateDto, ReservationDto> {

    @Autowired
    public ReservationController(ReservationService service) {
        super(service);
    }
}
