package com.toy.plany.repository;

import com.toy.plany.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Member, Long> {
    Optional<Member> findByEmployeeNum(String employeeNum);
}
