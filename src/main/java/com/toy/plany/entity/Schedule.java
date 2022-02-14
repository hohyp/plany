package com.toy.plany.entity;

import com.toy.plany.entity.enums.AlarmStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String title;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private AlarmStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime remindTime;

    @Builder
    public Schedule(Long id, Event event, User user, String title, AlarmStatus status, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime remindTime) {
        this.id = id;
        this.event = event;
        this.user = user;
        this.title = title;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remindTime = remindTime;
    }


    static public Schedule of(Event event, User user, String title, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime remindTime) {
        return Schedule.builder()
                .event(event)
                .user(user)
                .title(title)
                .startTime(startTime)
                .endTime(endTime)
                .remindTime(remindTime)
                .build();
    }

    public Schedule updateStatus(AlarmStatus status) {
        this.status = status;
        return this;
    }

}
