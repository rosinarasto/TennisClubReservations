package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.CourtDto;
import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.CourtCreateDto;
import com.tennisclub.reservations.mapper.CourtMapper;
import com.tennisclub.reservations.mapper.ReservationMapper;
import com.tennisclub.reservations.model.Court;
import com.tennisclub.reservations.repository.CourtRepository;
import com.tennisclub.reservations.service.CourtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CourtServiceImpl extends GenericCrudService<Court, CourtDto, CourtCreateDto, CourtDto> implements CourtService {

    private final CourtRepository courtRepository;

    private final ReservationMapper reservationMapper;

    @Autowired
    public CourtServiceImpl(CourtRepository repository, CourtMapper courtMapper, ReservationMapper reservationMapper) {
        super(repository, courtMapper, CourtDto.class, Court.class);
        this.courtRepository = repository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<ReservationDto> findReservations(int number) {
        var court = courtRepository.findByCourtNumber(number);

        return court.map(value ->
                        value.getReservations().stream()
                            .map(reservationMapper::toDto)
                            .toList())
                    .orElse(Collections.emptyList());
    }
}
