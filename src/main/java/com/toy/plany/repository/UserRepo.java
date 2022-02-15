package com.toy.plany.repository;

import com.toy.plany.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findUserByEmployeeNum(String employeeNum);

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    List<User> findUserByName(String Name);

    List<User> findUserByDepartmentId(Long departmentId);

    @Query("SELECT u FROM User u WHERE u.id IN (:users)")
    List<User> findUserByIdList(@Param("users") List<Long> userIdList);

    @Query("SELECT u FROM User u WHERE u.name LIKE ?1")
    List<User> findUserByNameAutoCompleted(String keyword);
}
