package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.config.ApiUris;
import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.UserDto;
import com.tennisclub.reservations.dto.create.UserCreateDto;
import com.tennisclub.reservations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiUris.USER_URI)
public class UserController extends GenericCrudController<UserDto, UserCreateDto, UserDto> {

    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        super(service);
        this.userService = service;
    }

    @GetMapping(ApiUris.USER_RESERVATIONS_URI)
    public ResponseEntity<List<ReservationDto>> getUserReservations(@PathVariable String phoneNumber) {
        try {
            var reservations = userService.findReservations(phoneNumber);
            return ResponseEntity.ok().body(reservations);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}