package com.toy.plany.repository;

import com.toy.plany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByEmployeeNum(String employeeNum);

    List<User> findUserByDepartmentId(Long departmentId);

    @Query("SELECT u FROM User u WHERE u.id IN (:users)")
    List<User> findUserByIdList(@Param("users") List<Long> userIdList);
}
