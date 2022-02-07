package com.toy.plany.exception.exceptions;

import com.toy.plany.exception.BusinessException;
import com.toy.plany.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
