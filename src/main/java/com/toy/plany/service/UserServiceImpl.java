package com.toy.plany.service;

import com.toy.plany.dto.dtos.TokenDto;
import com.toy.plany.dto.request.user.LoginRequest;
import com.toy.plany.dto.request.user.UpdatePasswordRequest;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.dto.response.auth.LoginResponse;
import com.toy.plany.entity.User;
import com.toy.plany.exception.exceptions.IncorrectPasswordException;
import com.toy.plany.exception.exceptions.UserNotFoundException;
import com.toy.plany.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest request, TokenDto tokenDto) {
        User user = findUserByEmployeeNumber(request.getEmployeeNumber());
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.encode("123456"));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword()))
            return LoginResponse.of(user, tokenDto);
        else
            throw new IncorrectPasswordException();
    }

    @Override
    @Transactional
    public UserResponse insertSlackUid(Long userId, String slackUid) {
        User user = findUserById(userId);
        user.updateSlackUid(slackUid);
        return UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    private User findUserByEmployeeNumber(String employeeNumber) {
        return userRepo.findUserByEmployeeNum(employeeNumber).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    private User findUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public UserResponse updatePassword(Long userId, UpdatePasswordRequest request) {
        User user = findUserById(userId);
        user.updatePassword(passwordEncoder.encode(request.getPassword()));
        return UserResponse.from(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> readMyDepartmentUser(Long departmentId) {
        List<User> userList = userRepo.findUserByDepartmentId(departmentId);
        return userList.stream().map(user -> UserResponse.from(user)).collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> readAutoCompleteUserList(String keyword) {
        //TODO 유저 검색 자동완성
        return null;
    }
}
