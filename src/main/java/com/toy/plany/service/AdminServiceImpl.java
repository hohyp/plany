package com.toy.plany.service;

import com.toy.plany.dto.request.admin.UserCreateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.entity.Department;
import com.toy.plany.entity.User;
import com.toy.plany.entity.enums.Color;
import com.toy.plany.exception.exceptions.DepartmentNotFoundException;
import com.toy.plany.exception.exceptions.UserNotFoundException;
import com.toy.plany.repository.DepartmentRepo;
import com.toy.plany.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
    private final DepartmentRepo departmentRepo;

    @Autowired
    public AdminServiceImpl(UserRepo userRepo, DepartmentRepo departmentRepo) {
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        Department department = getDepartmentById(request.getDepartmentId());
        Color color = getColorByRandom();
        User user = User.builder()
                .employeeNum(request.getEmployeeNum())
                .password(User.DEFAULT_PASSWORD)
                .email(request.getEmail())
                .name(request.getName())
                .department(department)
                .color(color)
                .position(request.getPosition())
                .build();
        User savedUser = userRepo.save(user);
        return createUserDto(savedUser);
    }

    @Override
    public UserResponse readUserByEmployeeNumber(String employeeNumber) {
        User user = findUserByEmployeeNum(employeeNumber);
        return createUserDto(user);
    }

    @Transactional(readOnly = true)
    private User findUserByEmployeeNum(String employeeNumber) {
        return userRepo.findByEmployeeNum(employeeNumber).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    private User findUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserResponse> readUserList() {
        return findAllUser().stream().map(user -> createUserDto(user)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    private List<User> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public List<UserResponse> deleteUser(Long userId) {
        deleteUserFromRepo(userId);
        return readUserList();
    }

    @Transactional
    private void deleteUserFromRepo(Long userId) {
        User user = findUserById(userId);
        //TODO 삭제 예외 에러 발생
        userRepo.delete(user);
    }

    @Transactional
    public DepartmentResponse createDepartment(String name) {
        Department department = Department.builder()
                .name(name)
                .build();
        Department savedDepartment = departmentRepo.save(department);
        return createDepartmentDto(savedDepartment);
    }

    @Override
    public List<DepartmentResponse> readDepartmentList() {
        return findAllDepartment().stream().map(this::createDepartmentDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    private List<Department> findAllDepartment() {
        return departmentRepo.findAll();
    }

    @Override
    public List<DepartmentResponse> deleteDepartment(Long departmentId) {
        deleteDepartmentFromRepo(departmentId);
        return readDepartmentList();
    }

    @Transactional
    private void deleteDepartmentFromRepo(Long departmentId) {
        Department department = getDepartmentById(departmentId);
        departmentRepo.delete(department);
    }

    private Color getColorByRandom() {
        //TODO Random return, 없으면 예외 발생하도록 수정
        return Color.RED;
    }

    private Department getDepartmentById(Long departmentId) {
        return departmentRepo.findById(departmentId).orElseThrow(DepartmentNotFoundException::new);
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

    private DepartmentResponse createDepartmentDto(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}
