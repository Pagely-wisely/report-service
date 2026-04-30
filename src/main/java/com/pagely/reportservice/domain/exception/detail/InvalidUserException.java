package com.pagely.reportservice.domain.exception.detail;

import com.pagely.reportservice.domain.exception.ReportErrorCode;
import com.pagely.reportservice.domain.exception.ReportException;

public class InvalidUserException extends ReportException {
    public InvalidUserException() {
        super(ReportErrorCode.USER_INVALID);
    }

    public InvalidUserException(ReportErrorCode errorCode) {
        super(errorCode);
    }
}
