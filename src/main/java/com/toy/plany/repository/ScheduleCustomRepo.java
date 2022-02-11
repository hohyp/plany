package com.toy.plany.repository;

import com.toy.plany.entity.User;

import java.util.List;

public interface ScheduleCustomRepo {
    List<Long> findEventIdListByUser(Long userId);
}
