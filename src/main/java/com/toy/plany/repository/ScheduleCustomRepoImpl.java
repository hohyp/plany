package com.toy.plany.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.plany.dto.dtos.FilteredEventDto;

import static com.toy.plany.entity.QSchedule.schedule;

import java.time.LocalDate;
import java.util.List;

public class ScheduleCustomRepoImpl implements ScheduleCustomRepo {
    private final JPAQueryFactory jpaQueryFactory;

    public ScheduleCustomRepoImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<FilteredEventDto> getEventInfo(Long userId, LocalDate startDate, LocalDate endDate) {
        return jpaQueryFactory.from(schedule)
                .select(Projections.constructor(FilteredEventDto.class, schedule.event.id, schedule.title, schedule.startTime, schedule.endTime))
                .where(schedule.user.id.eq(userId)
                        .and(schedule.startTime.after(startDate.atStartOfDay())
                                .and(schedule.endTime.before(endDate.plusDays(1).atStartOfDay())))
                )
                .fetch();
    }
}
