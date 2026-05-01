package com.pagely.reportservice.domain.exception;

import com.pagely.common.exception.BusinessException;
import com.pagely.common.exception.ErrorCode;

public class ReportException extends BusinessException {
    public ReportException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ReportException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
    }

    public ReportException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
