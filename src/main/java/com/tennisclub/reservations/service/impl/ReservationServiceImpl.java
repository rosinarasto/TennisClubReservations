package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.ReservationCreateDto;
import com.tennisclub.reservations.mapper.ReservationMapper;
import com.tennisclub.reservations.mapper.UserMapper;
import com.tennisclub.reservations.model.Reservation;
import com.tennisclub.reservations.repository.CourtRepository;
import com.tennisclub.reservations.repository.ReservationRepository;
import com.tennisclub.reservations.repository.UserRepository;
import com.tennisclub.reservations.service.ReservationService;
import com.tennisclub.reservations.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ReservationServiceImpl extends GenericCrudService<Reservation, ReservationDto, ReservationCreateDto, ReservationDto> implements ReservationService {

    private final UserService userService;

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final CourtRepository courtRepository;

    private final ReservationMapper reservationMapper;
    private final UserMapper userMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper mapper,
                                  UserRepository userRepository, UserService userService,
                                  UserMapper userMapper, CourtRepository courtRepository) {
        super(reservationRepository, mapper, ReservationDto.class, Reservation.class);
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.courtRepository = courtRepository;
        this.reservationMapper = mapper;
        this.userMapper = userMapper;
    }

    @Override
    public ReservationDto create(ReservationCreateDto createDto) {
        log.info("Creating new reservation {}", createDto);

        if (!validReservationDate(createDto)) {
            throw new IllegalArgumentException("Invalid reservation date");
        }

        var user = userRepository.findByPhoneNumber(createDto.getUser().getPhoneNumber());

        if (user.isEmpty()) {
            user = Optional.of(userMapper.toEntityFromDto(userService.create(createDto.getUser())));
        }

        var reservation = reservationMapper.toEntityFromCreateDto(createDto);
        reservation = reservationRepository.save(reservation);
        reservation.setUser(user.get());

        user.get().getReservations().add(reservation);

        return reservationMapper.toDto(reservation);
    }

    private boolean validReservationDate(ReservationCreateDto createDto) {
        var court = courtRepository.findById(createDto.getCourt().getId());

        return court.map(value -> value.getReservations().stream()
                .anyMatch(res -> (createDto.getFrom().isAfter(res.getFrom()) &&
                                  createDto.getFrom().isBefore(res.getTo())) ||
                                 (createDto.getTo().isBefore(res.getTo()) &&
                                  createDto.getTo().isAfter(res.getFrom()))))
                .orElse(false);
    }
}
