package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.create.ReservationCreateDto;
import com.tennisclub.reservations.mapper.ReservationMapper;
import com.tennisclub.reservations.model.Reservation;
import com.tennisclub.reservations.repository.ReservationRepository;
import com.tennisclub.reservations.repository.UserRepository;
import com.tennisclub.reservations.service.ReservationService;
import com.tennisclub.reservations.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
public class ReservationServiceImpl extends GenericCrudService<Reservation, ReservationDto, ReservationCreateDto, ReservationDto> implements ReservationService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper mapper,
                                  UserRepository userRepository, UserService userService) {
        super(reservationRepository, mapper, ReservationDto.class, Reservation.class);
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.reservationMapper = mapper;
    }

    @Override
    public ReservationDto create(ReservationCreateDto createDto) {
        log.info("Creating new reservation {}", createDto);

        var user = userRepository.findByPhoneNumber(createDto.getUser().getPhoneNumber());

        if (user.isEmpty()) {
            user = userRepository.findById(userService.create(createDto.getUser()).getId());
        }

        var reservation = reservationMapper.toEntityFromCreateDto(createDto);
        reservation = reservationRepository.save(reservation);
        reservation.setUser(user.get());

        user.get().getReservations().add(reservation);

        return reservationMapper.toDto(reservation);
    }
}
