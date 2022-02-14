package com.toy.plany.exception.exceptions;

import com.toy.plany.exception.BusinessException;
import com.toy.plany.exception.ErrorCode;

public class InvalidScheduleOwner extends BusinessException {
    public InvalidScheduleOwner() {
        super(ErrorCode.INVALIDATE_SCHEDULE_OWNER);
    }

    public InvalidScheduleOwner(String message) {
        super(ErrorCode.INVALIDATE_SCHEDULE_OWNER, message);
    }
}
