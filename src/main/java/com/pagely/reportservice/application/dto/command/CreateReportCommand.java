package com.pagely.reportservice.application.dto.command;

import com.pagely.reportservice.domain.model.ReadScope;
import java.util.UUID;

public record CreateReportCommand(
        String title,
        String content,
        ReadScope readScope,
        String bookId,
        UUID userId,
        UUID meetingId,
        UUID scheduleId
) {
}
