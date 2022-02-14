package com.toy.plany.entity;

import com.toy.plany.entity.enums.EventStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    @Id
    @Column(name = "SCHEDULE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Schedule(Event event, User user) {
        this.event = event;
        this.user = user;
    }

    static public Schedule from(Event event, User user){
        return Schedule.builder()
                .event(event)
                .user(user)
                .build();
    }

    public boolean validateOwner(User user){
        return user == this.user;
    }
}
