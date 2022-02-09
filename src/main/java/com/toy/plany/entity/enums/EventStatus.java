package com.toy.plany.entity.enums;

import lombok.Getter;

@Getter
public enum EventStatus {
    CREATED("Created"),
    REMINDED("Reminder"),
    CANCELED("Canceled");

    private String value;

    EventStatus(String value) {
        this.value = value;
    }
}
