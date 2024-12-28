package com.tennisclub.reservations.mapper;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.ReservationCreateDto;
import com.tennisclub.reservations.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends GenericMapper<Reservation, ReservationDto, ReservationCreateDto, ReservationDto> {
}
