package com.toy.plany.exception.exceptions;

import com.toy.plany.exception.BusinessException;
import com.toy.plany.exception.ErrorCode;

public class IncorrectPasswordException extends BusinessException {
    public IncorrectPasswordException() {
        super(ErrorCode.INCORRECT_PASSWORD);
    }
}
