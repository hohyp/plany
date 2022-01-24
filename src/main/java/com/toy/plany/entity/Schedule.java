package com.toy.plany.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseTimeEntity{
    @Id
    @Column(name = "SCHEDULE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDateTime startAt;

    private LocalDateTime finishAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REMINDER_ID")
    private Reminder reminder;

    @Builder
    public Schedule(User user, String title, String description, LocalDateTime startAt, LocalDateTime finishAt, Reminder reminder) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.reminder = reminder;
    }
}
