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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ReservationServiceImpl extends GenericCrudService<Reservation, ReservationDto, ReservationCreateDto, ReservationDto> implements ReservationService {

    private final UserService userService;

    private final CourtRepository courtRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    private final UserMapper userMapper;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper mapper,
                                  UserRepository userRepository, UserService userService,
                                  UserMapper userMapper, CourtRepository courtRepository) {
        super(reservationRepository, mapper, ReservationDto.class, Reservation.class);
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.reservationMapper = mapper;
        this.userMapper = userMapper;
        this.courtRepository = courtRepository;
    }

    @Override
    public ReservationDto create(ReservationCreateDto createDto) {
        log.info("Creating new reservation {}", createDto);

        var user = userRepository.findByPhoneNumber(createDto.getUser().getPhoneNumber());
        if (user.isEmpty()) {
            user = userRepository.findByName(createDto.getUser().getName());
            if (user.isEmpty()) {
                user = Optional.of(userMapper.toEntityFromDto(userService.create(createDto.getUser())));
            }
        }

        var reservation = reservationMapper.toEntityFromCreateDto(createDto);
        reservation = reservationRepository.save(reservation);
        reservation.setUser(user.get());

        user.get().getReservations().add(reservation);

        return reservationMapper.toDto(reservation);
    }

    public boolean isDateAvailable(int number, LocalDateTime from, LocalDateTime to) {
        log.info("isDateAvailable for court number {} from {} to {}", number, from, to);

        var court = courtRepository.findByCourtNumber(number);

        return court.map(value -> value.getReservations().stream()
                .noneMatch(res -> (from.isAfter(res.getFrom()) && from.isBefore(res.getTo())) ||
                                  (to.isAfter(res.getFrom())   && to.isBefore(res.getTo()))))
                .orElse(false);
    }
}
