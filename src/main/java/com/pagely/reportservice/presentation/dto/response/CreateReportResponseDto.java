package com.pagely.reportservice.presentation.dto.response;

import com.pagely.reportservice.application.dto.result.ReportResult;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateReportResponseDto(
        UUID reportId,
        LocalDateTime createdAt
) {
    public static CreateReportResponseDto from(ReportResult result) {
        return new CreateReportResponseDto(
                result.reportId(),
                result.createdAt()
        );
    }
}
