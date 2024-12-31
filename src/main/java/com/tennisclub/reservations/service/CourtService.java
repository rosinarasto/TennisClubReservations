package com.tennisclub.reservations.service;

import com.tennisclub.reservations.dto.CourtDto;
import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.CourtCreateDto;

import java.util.List;

public interface CourtService extends CrudService<CourtDto, CourtCreateDto, CourtDto> {

    List<ReservationDto> findReservations(int number);
}
