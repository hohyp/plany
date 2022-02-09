package com.toy.plany.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toy.plany.entity.enums.EventStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class Event extends BaseTimeEntity {
    @Id
    @Column(name = "EVENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User organizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Schedule> scheduleList = new ArrayList<>();

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Builder
    public Event(Long id, String title, String description, User organizer, List<Schedule> scheduleList, LocalDateTime startTime, LocalDateTime endTime, EventStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.organizer = organizer;
        this.scheduleList = scheduleList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Event updateScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
        return this;
    }

    public Event updateStatus(EventStatus status){
        this.status = status;
        return this;
    }
}
