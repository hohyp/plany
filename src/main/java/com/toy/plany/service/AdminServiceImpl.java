package com.toy.plany.service;

import com.toy.plany.dto.request.admin.UserCreateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.entity.Authority;
import com.toy.plany.entity.Department;
import com.toy.plany.entity.User;
import com.toy.plany.entity.enums.Color;
import com.toy.plany.exception.exceptions.DeleteFailException;
import com.toy.plany.exception.exceptions.DepartmentNotFoundException;
import com.toy.plany.exception.exceptions.UserNotFoundException;
import com.toy.plany.repository.DepartmentRepo;
import com.toy.plany.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
    private final DepartmentRepo departmentRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(UserRepo userRepo, DepartmentRepo departmentRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        Department department = getDepartmentById(request.getDepartmentId());
        Color color = getColorByRandom();

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .employeeNum(request.getEmployeeNum())
                .password(passwordEncoder.encode(User.DEFAULT_PASSWORD))
                .name(request.getName())
                .department(department)
                .color(color)
                .note(request.getNote())
                .authorities(Collections.singleton(authority))
                .build();
        User savedUser = userRepo.save(user);
        return UserResponse.from(savedUser);
    }

    @Override
    public UserResponse readUserByEmployeeNumber(String employeeNumber) {
        User user = findUserByEmployeeNum(employeeNumber);
        return UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    private User findUserByEmployeeNum(String employeeNumber) {
        return userRepo.findUserByEmployeeNum(employeeNumber).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    private User findUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserResponse> readUserList() {
        return findAllUser().stream().map(user -> UserResponse.from(user)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    private List<User> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public Boolean deleteUser(Long userId) {
        return deleteUserFromRepo(userId);
    }

    @Override
    public Boolean deleteUser(List<Long> userIdList) {
        for (Long userId : userIdList)
            deleteUserFromRepo(userId);

        return true;
    }

    @Transactional
    private Boolean deleteUserFromRepo(Long userId) {
        try {
            userRepo.deleteById(userId);
            return true;
        } catch (Exception e) {
            throw new DeleteFailException();
        }
    }

    @Transactional
    public DepartmentResponse createDepartment(String name) {
        Department department = Department.builder()
                .name(name)
                .build();
        Department savedDepartment = departmentRepo.save(department);
        return DepartmentResponse.from(savedDepartment);
    }

    @Override
    public List<DepartmentResponse> readDepartmentList() {
        return findAllDepartment().stream().map(department -> DepartmentResponse.from(department)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    private List<Department> findAllDepartment() {
        return departmentRepo.findAll();
    }

    @Override
    public Boolean deleteDepartment(Long departmentId) {
        return deleteDepartmentFromRepo(departmentId);
    }

    @Transactional
    private Boolean deleteDepartmentFromRepo(Long departmentId) {
        try {
            departmentRepo.deleteById(departmentId);
            return true;
        } catch (Exception e) {
            throw new DeleteFailException();
        }
    }

    private Color getColorByRandom() {
        //TODO Random return, 없으면 예외 발생하도록 수정
        return Color.RED;
    }

    @Transactional(readOnly = true)
    private Department getDepartmentById(Long departmentId) {
        return departmentRepo.findById(departmentId).orElseThrow(DepartmentNotFoundException::new);
    }
}
