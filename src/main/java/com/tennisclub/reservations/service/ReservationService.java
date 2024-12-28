package com.tennisclub.reservations.service;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.ReservationCreateDto;

public interface ReservationService extends CrudService<ReservationDto, ReservationCreateDto, ReservationDto> {
}
