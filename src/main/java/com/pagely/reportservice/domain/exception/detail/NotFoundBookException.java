package com.pagely.reportservice.domain.exception.detail;

import com.pagely.reportservice.domain.exception.ReportErrorCode;
import com.pagely.reportservice.domain.exception.ReportException;

public class NotFoundBookException extends ReportException {
    public NotFoundBookException() {
        super(ReportErrorCode.BOOK_NOT_FOUND);
    }
}
