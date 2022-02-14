package com.toy.plany.entity;

import com.toy.plany.entity.enums.Color;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    private String note;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})

    private Set<Authority> authorities;

    @Builder
    public User(Long id, String employeeNum, String password, String slackUid, String name, Department department, Color color, String note, Set<Authority> authorities) {
        this.id = id;
        this.employeeNum = employeeNum;
        this.password = password;
        this.slackUid = slackUid;
        this.name = name;
        this.department = department;
        this.color = color;
        this.note = note;
        this.authorities = authorities;
    }

    public void updateSlackUid(String slackUid) {
        this.slackUid = slackUid;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
