package com.toy.plany.exception.exceptions;

import com.toy.plany.exception.BusinessException;
import com.toy.plany.exception.ErrorCode;

public class SaveFailException extends BusinessException {
    public SaveFailException() {
        super(ErrorCode.SAVE_FAILED);
    }
}
