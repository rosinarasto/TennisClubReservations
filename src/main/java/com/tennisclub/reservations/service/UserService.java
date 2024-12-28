package com.tennisclub.reservations.service;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.UserDto;
import com.tennisclub.reservations.dto.create.UserCreateDto;

import java.util.List;

public interface UserService extends CrudService<UserDto, UserCreateDto, UserDto> {

    List<ReservationDto> findReservations(String phoneNumber);
}
