package com.pagely.reportservice.domain.exception.detail;

import com.pagely.reportservice.domain.exception.ReportErrorCode;
import com.pagely.reportservice.domain.exception.ReportException;

public class MisMatchReadScopeException extends ReportException {
    public MisMatchReadScopeException() {
        super(ReportErrorCode.READ_SCOPE_MISMATCH);
    }
}
