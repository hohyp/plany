package com.toy.plany.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.toy.plany.entity.QSchedule.schedule;

import java.util.List;

public class ScheduleCustomRepoImpl implements ScheduleCustomRepo {
    private final JPAQueryFactory jpaQueryFactory;

    public ScheduleCustomRepoImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Long> findEventIdListByUser(Long userId) {
        return jpaQueryFactory.from(schedule)
                .select(schedule.event.id)
                .where(schedule.user.id.eq(userId))
                .fetch();
    }
}
