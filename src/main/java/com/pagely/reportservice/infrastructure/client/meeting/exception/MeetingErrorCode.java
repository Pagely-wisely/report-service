package com.pagely.reportservice.infrastructure.client.meeting.exception;

import com.pagely.common.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MeetingErrorCode implements ErrorCode {
    MEETING_NOT_FOUND("존재하지 않는 모임 입니다.", HttpStatus.NOT_FOUND),
    ;

    private final String message;
    private final HttpStatus httpStatus;
    private final String code;

    MeetingErrorCode(String message, HttpStatus httpStatus) {
        this.code = this.name();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
