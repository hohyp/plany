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
public class Meeting extends BaseTimeEntity{
    @Id
    @Column(name = "MEETING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime finishAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REMINDER_ID")
    private Reminder reminder;

    @Builder
    public Meeting(String title, String description, LocalDateTime startAt, LocalDateTime finishAt, Reminder reminder) {
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.reminder = reminder;
    }
}
