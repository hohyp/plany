package com.toy.plany.service;

import com.toy.plany.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

}
