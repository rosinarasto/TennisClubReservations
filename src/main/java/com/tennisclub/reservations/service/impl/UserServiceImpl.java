package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.dto.UserDto;
import com.tennisclub.reservations.dto.create.UserCreateDto;
import com.tennisclub.reservations.exception.ResourceAlreadyExistsException;
import com.tennisclub.reservations.mapper.ReservationMapper;
import com.tennisclub.reservations.mapper.UserMapper;
import com.tennisclub.reservations.model.User;
import com.tennisclub.reservations.repository.UserRepository;
import com.tennisclub.reservations.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl extends GenericCrudService<User, UserDto, UserCreateDto, UserDto> implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final ReservationMapper reservationMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper,
                           ReservationMapper reservationMapper, PasswordEncoder passwordEncoder) {
        super(repository, mapper, UserDto.class, User.class);
        this.userRepository = repository;
        this.userMapper = mapper;
        this.reservationMapper = reservationMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto create(UserCreateDto newUser) {
        log.info("Creating new User: {}", newUser);

        var userByPhoneNumber = userRepository.findByPhoneNumber(newUser.getPhoneNumber());

        if (userByPhoneNumber.isPresent()) {
            throw new ResourceAlreadyExistsException("user with given phone number already exists");
        }

        var userByName = userRepository.findByName(newUser.getName());

        if (userByName.isPresent()) {
            throw new ResourceAlreadyExistsException("user with given name already exists");
        }

        var user = userMapper.toEntityFromCreateDto(newUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public Optional<UserDto> update(UserDto updateUser) {
        log.info("Updating User: {}", updateUser);

        var user = userMapper.toEntityFromUpdateDto(updateUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

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
                                .filter(res -> future && res.getFrom().isAfter(LocalDateTime.now()))
                                .toList())
                    .orElse(Collections.emptyList());
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }
}
