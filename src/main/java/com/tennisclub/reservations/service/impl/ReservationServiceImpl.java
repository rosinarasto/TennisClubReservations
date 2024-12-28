package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.ReservationCreateDto;
import com.tennisclub.reservations.mapper.ReservationMapper;
import com.tennisclub.reservations.model.Reservation;
import com.tennisclub.reservations.repository.ReservationRepository;
import com.tennisclub.reservations.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReservationServiceImpl extends GenericCrudService<Reservation, ReservationDto, ReservationCreateDto, ReservationDto> implements ReservationService {

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper mapper) {
        super(reservationRepository, mapper, ReservationDto.class, Reservation.class);
    }
}
