package com.toy.plany.service;

import com.toy.plany.dto.request.UserCreateRequest;
import com.toy.plany.repository.MemberRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService, UserDetailsService {

    private final MemberRepo userRepo;

    public UserServiceImpl(MemberRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Long save(UserCreateRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeNum) throws UsernameNotFoundException {
        return userRepo.findByEmployeeNum(employeeNum)
                .orElseThrow(() -> new UsernameNotFoundException((employeeNum)));
    }
}
