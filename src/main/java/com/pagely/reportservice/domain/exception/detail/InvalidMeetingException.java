package com.pagely.reportservice.domain.exception.detail;

import com.pagely.reportservice.domain.exception.ReportErrorCode;
import com.pagely.reportservice.domain.exception.ReportException;

public class InvalidMeetingException extends ReportException {
    public InvalidMeetingException() {
        super(ReportErrorCode.MEETING_INVALID);
    }
}
