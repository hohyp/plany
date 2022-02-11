package com.toy.plany.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.toy.plany.dto.dtos.FilteredEventDto;
import static com.toy.plany.entity.QEvent.event;

import com.toy.plany.entity.Schedule;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class EventCustomRepoImpl extends QuerydslRepositorySupport implements EventCustomRepo {
    private final JPAQueryFactory jpaQueryFactory;

    public EventCustomRepoImpl(JPAQueryFactory jpaQueryFactory) {
        super(Schedule.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public List<FilteredEventDto> getEventInfo(List<Long> eventIdList) {
        System.out.println(eventIdList);
        for(Long l : eventIdList){
            System.out.println(l);
        }
        return jpaQueryFactory.from(event)
                .select(Projections.constructor(FilteredEventDto.class, event.id, event.title, event.startTime, event.endTime))
                .where(event.id.in(eventIdList))
                .fetch();
    }
}
