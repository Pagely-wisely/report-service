package com.pagely.reportservice.infrastructure.client.meeting.exception;

import com.pagely.common.exception.BusinessException;
import com.pagely.common.exception.ErrorCode;

public class MeetingException extends BusinessException {
    public MeetingException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MeetingException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
    }

    public MeetingException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
