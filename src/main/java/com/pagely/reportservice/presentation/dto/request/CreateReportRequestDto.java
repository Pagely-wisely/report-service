package com.pagely.reportservice.presentation.dto.request;

import com.pagely.reportservice.application.dto.command.CreateReportCommand;
import com.pagely.reportservice.domain.model.ReadScope;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.hibernate.validator.constraints.Length;

public record CreateReportRequestDto(
        @NotBlank(message = "제목은 필수 입력 항목입니다.")
        @Length(max = 255, message = "제목은 255자 이내로 입력해주세요")
        String title,

        @NotBlank(message = "내용은 필수 입력 항목입니다.")
        String content,

        @NotNull(message = "공개 범위 설정은 필수 항목입니다.")
        ReadScope readScope,

        @NotBlank(message = "도서 정보는 필수 입력 항목입니다.")
        @Length(max = 20, message = "잘못된 도서 아이디 입니다.")
        String bookId,

        UUID meetingId,

        UUID scheduleId
) {
    public CreateReportCommand toCommand(UUID userId) {
        return new CreateReportCommand(
                title,
                content,
                readScope,
                bookId,
                userId,
                meetingId,
                scheduleId
        );
    }
}
