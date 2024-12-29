package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.UserDto;
import com.tennisclub.reservations.dto.create.UserCreateDto;
import com.tennisclub.reservations.mapper.ReservationMapper;
import com.tennisclub.reservations.mapper.UserMapper;
import com.tennisclub.reservations.model.User;
import com.tennisclub.reservations.repository.UserRepository;
import com.tennisclub.reservations.service.UserService;
import com.tennisclub.reservations.util.PasswordUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl extends GenericCrudService<User, UserDto, UserCreateDto, UserDto> implements UserService {

    private final UserRepository userRepository;

    private final ReservationMapper reservationMapper;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper, ReservationMapper reservationMapper) {
        super(repository, mapper, UserDto.class, User.class);
        this.userRepository = repository;
        this.userMapper = mapper;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public UserDto create(UserCreateDto newUser) {
        log.info("Creating new User: {}", newUser);

        var user = userMapper.toEntityFromSaveDto(newUser);
        var salt = PasswordUtil.generateSalt();
        user.setSalt(salt);
        user.setPassword(PasswordUtil.hashPassword(user.getPassword(), salt));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public Optional<UserDto> update(UserDto updateUser) {
        log.info("Updating User: {}", updateUser);

        var user = userMapper.toEntityFromUpdateDto(updateUser);
        user.setPassword(PasswordUtil.hashPassword(user.getPassword(), user.getSalt()));

        if (findById(user.getId()).isPresent())
            return Optional.ofNullable(userMapper.toDto(userRepository.update(user)));

        return Optional.empty();
    }

    @Override
    public List<ReservationDto> findReservations(String phoneNumber, boolean future) {
        var user = userRepository.findByPhoneNumber(phoneNumber);

        return user.map(value ->
                        value.getReservations().stream()
                            .map(reservationMapper::toDto)
                                .filter(res -> future && res.getFrom().isAfter(LocalDate.now()))
                                .toList())
                    .orElse(Collections.emptyList());
    }
}
