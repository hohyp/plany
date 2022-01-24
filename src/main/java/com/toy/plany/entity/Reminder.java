package com.toy.plany.entity;

import com.toy.plany.entity.enums.RemindStatus;
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
public class Reminder extends BaseTimeEntity{
    @Id
    @Column(name = "REMINDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private LocalDateTime remindAt;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private RemindStatus status;

    @Builder
    public Reminder(Schedule schedule, Member member, LocalDateTime remindAt, RemindStatus status) {
        this.schedule = schedule;
        this.member = member;
        this.remindAt = remindAt;
        this.status = status;
    }
}
