package com.toy.plany.exception.exceptions;

import com.toy.plany.exception.BusinessException;
import com.toy.plany.exception.ErrorCode;

public class InsufficientColorException extends BusinessException {
    public InsufficientColorException() {
        super(ErrorCode.INSUFFICIENT_COLOR_ERROR);
    }
}
