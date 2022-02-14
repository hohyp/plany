package com.toy.plany.entity.enums;

import lombok.Getter;

@Getter
public enum AlarmStatus {
    CREATED("Created"),
    REMINDED("Reminder"),
    CANCELED("Canceled");

    private String value;

    AlarmStatus(String value) {
        this.value = value;
    }
}
