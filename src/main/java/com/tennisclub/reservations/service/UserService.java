package com.tennisclub.reservations.service;

import com.tennisclub.reservations.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User save(User user);
}
