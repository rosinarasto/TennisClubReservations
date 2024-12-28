package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.UserDto;
import com.tennisclub.reservations.dto.create.UserCreateDto;
import com.tennisclub.reservations.mapper.ReservationMapper;
import com.tennisclub.reservations.mapper.UserMapper;
import com.tennisclub.reservations.model.User;
import com.tennisclub.reservations.repository.UserRepository;
import com.tennisclub.reservations.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends GenericCrudService<User, UserDto, UserCreateDto, UserDto> implements UserService {

    private final UserRepository userRepository;

    private final ReservationMapper reservationMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper, ReservationMapper reservationMapper) {
        super(repository, mapper, UserDto.class, User.class);
        this.userRepository = repository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public UserDto create(UserCreateDto newUser) {
        return super.create(newUser);
    }

    @Override
    public Optional<UserDto> update(UserDto updateUser) {
        return super.update(updateUser);
    }

    @Override
    public List<ReservationDto> findReservations(String phoneNumber) {
        var user = userRepository.findByPhoneNumber(phoneNumber);

        return user.map(value ->
                        value.getReservations().stream()
                            .map(reservationMapper::toDto)
                            .toList())
                    .orElse(Collections.emptyList());
    }
}
