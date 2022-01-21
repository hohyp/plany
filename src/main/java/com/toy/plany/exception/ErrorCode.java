package com.toy.plany.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //Common
    INVALID_INPUT_VALUE("C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED("C002", "Method Not Allowed"),
    ENTITY_NOT_FOUND("C003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR("C004", "Internal Server Error"),
    INVALID_TYPE_VALUE("C005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED("C006", "Access Denied"),

    //Meeting
    MEETING_NOT_FOUND("M1001", "Meeting Not Found"),

    //Schedule
    SCHEDULE_NOT_FOUND("S1001", "Schedule Not Found"),

    //User
    USER_NOT_FOUND("U1001", "User Not Found");

    private final String code;
    private final String message;

    ErrorCode(final String code, final String message) {
        this.message = message;
        this.code = code;
    }
}
