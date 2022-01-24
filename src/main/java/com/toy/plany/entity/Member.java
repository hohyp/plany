package com.toy.plany.entity;

import com.toy.plany.entity.enums.Color;
import com.toy.plany.entity.enums.MemberPosition;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeNum;

    @Column(nullable = false)
    private String password;

//    @Column(name = "auth")
//    private String auth;

    @Column(nullable = false)
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

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberPosition position;

    @Builder
    public Member(String employeeNum, String password, String slackUid, String name, String email, Department department, Color color) {
        this.employeeNum = employeeNum;
        this.password = password;
        this.slackUid = slackUid;
        this.name = name;
        this.email = email;
        this.department = department;
        this.color = color;
    }
}
