package com.toy.plany.exception.exceptions;

import com.toy.plany.exception.BusinessException;
import com.toy.plany.exception.ErrorCode;

public class InvalidOrganizerException extends BusinessException {
    public InvalidOrganizerException() {
        super(ErrorCode.INVALID_ORGANIZER);
    }
}
