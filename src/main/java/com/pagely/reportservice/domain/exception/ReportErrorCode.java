package com.pagely.reportservice.domain.exception;

import com.pagely.common.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ReportErrorCode implements ErrorCode {

    USER_INVALID("잘못된 사용자 정보입니다.", HttpStatus.BAD_REQUEST),
    USER_NULL("사용자 정보가 비어있습니다.", HttpStatus.BAD_REQUEST),

    MEETING_INVALID("잘못된 모임 정보입니다.", HttpStatus.BAD_REQUEST),

    READ_SCOPE_MISMATCH("공개 범위가 잘못 설정됐습니다.", HttpStatus.BAD_REQUEST),

    MEETING_ACCESS_FORBIDDER("해당 모임에 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    MEETING_CREATE_FORBIDDEN("해당 모임에 독후감 작성 권한이 없습니다.", HttpStatus.FORBIDDEN),
    ;

    private final String message;
    private final HttpStatus httpStatus;
    private final String code;

    ReportErrorCode(String message, HttpStatus httpStatus) {
        this.code = this.name();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
