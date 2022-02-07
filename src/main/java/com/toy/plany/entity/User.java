package com.toy.plany.entity;

import com.toy.plany.entity.enums.Color;
import com.toy.plany.entity.enums.UserPosition;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    public static final String DEFAULT_PASSWORD = "123456";

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String employeeNum;

    @Column(nullable = false)
    private String password;

    private String slackUid;

    @Column(nullable = false)
    private String name;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    private String position;

    @Builder
    public User(String employeeNum, String password, String slackUid, String name, String email, Department department, Color color, String position) {
        this.employeeNum = employeeNum;
        this.password = password;
        this.slackUid = slackUid;
        this.name = name;
        this.email = email;
        this.department = department;
        this.color = color;
        this.position = position;
    }

    public void updateSlackUid(String slackUid) {
        this.slackUid = slackUid;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
