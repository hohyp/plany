package com.toy.plany.repository;

import com.toy.plany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmployeeNum(String employeeNum);


    List<User> findByDepartmentId(Long departmentId);
}
