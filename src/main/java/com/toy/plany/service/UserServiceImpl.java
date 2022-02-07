package com.toy.plany.service;

import com.toy.plany.dto.request.user.LoginRequest;
import com.toy.plany.dto.request.user.UpdatePasswordRequest;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.entity.User;
import com.toy.plany.exception.exceptions.IncorrectPasswordException;
import com.toy.plany.exception.exceptions.UserNotFoundException;
import com.toy.plany.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = findUserByEmployeeNumber(request.getEmployeeNumber());
        if(user.getPassword().equals(request.getPassword()))
            return createUserDto(user);
        else
            throw new IncorrectPasswordException();
    }

    @Override
    @Transactional
    public UserResponse insertSlackUid(Long userId, String slackUid) {
        User user = findUserById(userId);
        user.updateSlackUid(slackUid);
        return createUserDto(user);
    }

    @Transactional(readOnly = true)
    private User findUserByEmployeeNumber(String employeeNumber) {
        return userRepo.findByEmployeeNum(employeeNumber).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    private User findUserById(Long userId){
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public UserResponse updatePassword(Long userId, UpdatePasswordRequest request) {
        User user = findUserById(userId);
        user.updatePassword(request.getPassword());
        return createUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> readMyDepartmentUser(Long departmentId) {
        List<User> userList = userRepo.findByDepartmentId(departmentId);
        return userList.stream().map(user -> createUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> readAutoCompleteUserList(String keyword) {
        //TODO 유저 검색 자동완성
        return null;
    }

    private UserResponse createUserDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .employeeNum(user.getEmployeeNum())
                .name(user.getName())
                .color(user.getColor().getCode())
                .fontColor(user.getColor().getFontColor().getCode())
                .department(user.getDepartment().getName())
                .position(user.getPosition())
                .slackUid(user.getSlackUid())
                .email(user.getEmail())
                .build();
    }
}
