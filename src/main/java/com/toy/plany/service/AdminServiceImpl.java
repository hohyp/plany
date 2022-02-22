package com.toy.plany.service;

import com.toy.plany.dto.request.admin.UserCreateRequest;
import com.toy.plany.dto.request.admin.UserUpdateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.entity.Authority;
import com.toy.plany.entity.Color;
import com.toy.plany.entity.Department;
import com.toy.plany.entity.User;
import com.toy.plany.entity.enums.Colors;
import com.toy.plany.entity.enums.FontColor;
import com.toy.plany.exception.exceptions.DeleteFailException;
import com.toy.plany.exception.exceptions.DepartmentNotFoundException;
import com.toy.plany.exception.exceptions.InsufficientColorException;
import com.toy.plany.exception.exceptions.UserNotFoundException;
import com.toy.plany.repository.ColorRepo;
import com.toy.plany.repository.DepartmentRepo;
import com.toy.plany.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
    private final DepartmentRepo departmentRepo;
    private final PasswordEncoder passwordEncoder;
    private final ColorRepo colorRepo;

    @Autowired
    public AdminServiceImpl(UserRepo userRepo, DepartmentRepo departmentRepo, PasswordEncoder passwordEncoder, ColorRepo colorRepo) {
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
        this.passwordEncoder = passwordEncoder;
        this.colorRepo = colorRepo;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        Department department = getDepartmentById(request.getDepartmentId());

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Color color = getColorByRandom();

        User user = User.builder()
                .employeeNum(request.getEmployeeNum())
                .password(passwordEncoder.encode(User.DEFAULT_PASSWORD))
                .name(request.getUserName())
                .department(department)
                .color(color)
                .note(request.getNote())
                .authorities(Collections.singleton(authority))
                .build();
        User savedUser = userRepo.save(user);
        return UserResponse.from(savedUser);
    }

    @Transactional
    public Color getColorByRandom() {
        List<Color> colorList = getUnusedColorList();
        final int size = colorList.size();
        if (size == 0)
            throw new InsufficientColorException();
        Random random = new Random();
        Color color = colorList.get(random.nextInt(size));
        color.use();
        return color;
    }

    @Transactional
    public Color addColor(Colors colors){
        Color color = Color.builder()
                .color(colors)
                .build();
        return colorRepo.save(color);
    }

    @Transactional
    public List<Color> getUnusedColorList() {
        return colorRepo.findAllColorUnused();
    }


    @Override
    public UserResponse readUserByEmployeeNumber(String employeeNumber) {
        User user = findUserByEmployeeNum(employeeNumber);
        return UserResponse.from(user);
    }

    @Override
    @Transactional
    public List<UserResponse> readUserByName(String name) {
        return findUserByName(name).stream().map(user -> UserResponse.from(user)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public User findUserByEmployeeNum(String employeeNumber) {
        return userRepo.findUserByEmployeeNum(employeeNumber).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<User> findUserByName(String name) {
        return userRepo.findUserByName(name);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserResponse> readUserList() {
        return findAllUser().stream().map(user -> UserResponse.from(user)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<User> findAllUser() {
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
    public Boolean deleteUserFromRepo(Long userId) {
        try {
            User user = findUserById(userId);
            user.getColor().unUse();
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

    @Override
    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = findUserById(userId);
        user.setEmployeeNum(request.getEmployeeNum());
        Department department = getDepartmentById(request.getDepartmentId());
        user.setDepartment(department);
        user.setName(request.getUserName());
        user.setNote(request.getNote());
        return UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    public List<Department> findAllDepartment() {
        return departmentRepo.findAll();
    }

    @Override
    public Boolean deleteDepartment(Long departmentId) {
        return deleteDepartmentFromRepo(departmentId);
    }

    @Transactional
    public Boolean deleteDepartmentFromRepo(Long departmentId) {
        try {
            departmentRepo.deleteById(departmentId);
            return true;
        } catch (Exception e) {
            throw new DeleteFailException();
        }
    }

    @Transactional(readOnly = true)
    public Department getDepartmentById(Long departmentId) {
        return departmentRepo.findById(departmentId).orElseThrow(DepartmentNotFoundException::new);
    }
}
