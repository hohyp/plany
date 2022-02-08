package com.toy.plany.exception.exceptions;

import com.toy.plany.exception.BusinessException;
import com.toy.plany.exception.ErrorCode;

public class DeleteFailException extends BusinessException {
    public DeleteFailException() {
        super(ErrorCode.DELETE_FAILED);
    }
}
