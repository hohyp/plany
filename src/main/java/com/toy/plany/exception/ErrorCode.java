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
    DELETE_FAILED("C007", "Delete Failed"),
    SAVE_FAILED("C008", "Save Failed"),

    //Event
    EVENT_NOT_FOUND("E001", "Event Not Found"),
    INVALID_ORGANIZER("E002", "Invalid Organizer"),

    //Schedule
    SCHEDULE_NOT_FOUND("S001", "Schedule Not Found"),
    INVALIDATE_SCHEDULE_OWNER("S002","Invalid Schedule Owner"),

    //User
    USER_NOT_FOUND("U001", "User Not Found"),
    INCORRECT_PASSWORD("U002", "Incorrect Password"),
    INVALID_PASSWORD("U003", "InValid Password"),

    //Color
    INSUFFICIENT_COLOR_ERROR("C001", "Insufficient Color Error"),

    //Department
    DEPARTMENT_NOT_FOUND("D001", "Department Not Found");

    private final String code;
    private final String message;

    ErrorCode(final String code, final String message) {
        this.message = message;
        this.code = code;
    }
}
