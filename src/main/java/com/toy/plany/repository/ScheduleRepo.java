package com.toy.plany.repository;

import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long>, ScheduleCustomRepo {
    @Query("SELECT s FROM Schedule s WHERE s.user = ?1")
    List<Schedule> findScheduleByUser(User user);

    @Query("SELECT s FROM Schedule s WHERE s.user in ?1")
    List<Schedule> findScheduleByUserList(List<User> userList);
}
