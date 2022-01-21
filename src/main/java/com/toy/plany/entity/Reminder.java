package com.toy.plany.entity;

import com.toy.plany.entity.enums.RemindStatus;
import com.toy.plany.entity.enums.RemindType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reminder {
    @Id
    @Column(name = "REMINDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime remindAt;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private RemindStatus status;
}
