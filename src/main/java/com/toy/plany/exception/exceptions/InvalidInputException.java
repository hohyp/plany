package com.toy.plany.exception.exceptions;

import com.toy.plany.exception.BusinessException;
import com.toy.plany.exception.ErrorCode;

public class InvalidInputException extends BusinessException {
    public InvalidInputException() {
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
}
