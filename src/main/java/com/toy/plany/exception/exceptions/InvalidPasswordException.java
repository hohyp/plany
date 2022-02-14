package com.toy.plany.exception.exceptions;

import com.toy.plany.exception.BusinessException;
import com.toy.plany.exception.ErrorCode;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }

    public InvalidPasswordException(String message) {
        super(ErrorCode.INVALID_PASSWORD, message);
    }
}
