package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByName(String name);
}
