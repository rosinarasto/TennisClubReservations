package com.tennisclub.reservations.service;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.ReservationCreateDto;
import com.tennisclub.reservations.model.Court;

import java.time.LocalDateTime;

public interface ReservationService extends CrudService<ReservationDto, ReservationCreateDto, ReservationDto> {

    boolean isDateAvailable(Court court, LocalDateTime from, LocalDateTime to);
}
