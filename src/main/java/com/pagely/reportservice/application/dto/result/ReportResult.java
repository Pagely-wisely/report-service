package com.pagely.reportservice.application.dto.result;

import com.pagely.reportservice.domain.model.ReadScope;
import com.pagely.reportservice.domain.model.Report;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record ReportResult(
        UUID reportId,
        String title,
        String content,
        ReadScope readScope,
        String bookId,
        UUID userId,
        UUID meetingId,
        UUID scheduleId,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy
) {
    public static ReportResult from(Report report) {
        return new ReportResult(
                report.getId(),
                report.getTitle(),
                report.getContent(),
                report.getReadScope(),
                report.getBookId(),
                report.getUserId(),
                Objects.isNull(report.getMeeting()) ?
                        null : report.getMeeting().getId(),
                Objects.isNull(report.getMeeting()) ?
                        null : report.getMeeting().getScheduleId(),
                report.getCreatedAt(),
                report.getCreatedBy(),
                report.getUpdatedAt(),
                report.getUpdatedBy()
        );
    }
}
