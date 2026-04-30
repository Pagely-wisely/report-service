package com.pagely.reportservice.domain.exception.detail;

import com.pagely.reportservice.domain.exception.ReportErrorCode;
import com.pagely.reportservice.domain.exception.ReportException;

public class NoPermissionMeetingException extends ReportException {
    public NoPermissionMeetingException() {
        super(ReportErrorCode.MEETING_ACCESS_FORBIDDER);
    }

    public NoPermissionMeetingException(ReportErrorCode errorCode) {
        super(errorCode);
    }
}
