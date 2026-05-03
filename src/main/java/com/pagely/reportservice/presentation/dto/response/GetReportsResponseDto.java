package com.pagely.reportservice.presentation.dto.response;

import com.pagely.reportservice.application.dto.result.ReportResult;
import com.pagely.reportservice.domain.model.ReadScope;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record GetReportsResponseDto(
        UUID id,
        String title,
        String content,
        ReadScope readScope,
        String bookId,
        UUID userId,
        UUID meetingId,
        UUID scheduleId,
        LocalDateTime createdAt
) {
    public static GetReportsResponseDto from(ReportResult result) {
        return GetReportsResponseDto.builder()
                .id(result.reportId())
                .title(result.title())
                .content(result.content())
                .readScope(result.readScope())
                .bookId(result.bookId())
                .userId(result.userId())
                .meetingId(result.meetingId())
                .scheduleId(result.scheduleId())
                .createdAt(result.createdAt())
                .build();
    }
}
