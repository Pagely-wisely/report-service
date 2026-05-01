package com.pagely.reportservice.infrastructure.client.meeting.exception.detail;

import com.pagely.reportservice.infrastructure.client.meeting.exception.MeetingErrorCode;
import com.pagely.reportservice.infrastructure.client.meeting.exception.MeetingException;

public class NotFountMeetingException extends MeetingException {
    public NotFountMeetingException() {
        super(MeetingErrorCode.MEETING_NOT_FOUND);
    }
}
