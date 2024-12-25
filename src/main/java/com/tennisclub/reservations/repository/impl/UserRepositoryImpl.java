package com.tennisclub.reservations.repository.impl;

import com.tennisclub.reservations.model.User;
import com.tennisclub.reservations.repository.UserRepository;

public class UserRepositoryImpl extends GenericCrudRepository<User> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }
}
