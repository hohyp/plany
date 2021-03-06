package com.toy.plany.entity;

import com.toy.plany.entity.enums.Colors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLOR_ID")
    private Long id;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Colors color;

    private Boolean isUsed;

    @Builder
    public Color(Long id, Colors color) {
        this.id = id;
        this.color = color;
        this.isUsed = false;
    }

    public void use() {
        this.isUsed = true;
    }

    public void unUse(){
        this.isUsed = false;
    }
}
