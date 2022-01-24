package com.toy.plany.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("MEETING")
public class Attendants extends Event{
    @Id
    @Column(name = "ATTENDANTS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTENDANT_ID")
    private User attendant;

    @Builder
    public Attendants(User attendant) {
        this.attendant = attendant;
    }
}
