package com.pagely.reportservice.domain.event.payload;

import com.pagely.reportservice.application.dto.result.BookResult;
import com.pagely.reportservice.application.dto.result.ReportResult;
import com.pagely.reportservice.domain.event.BaseEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReportCreatedEvent extends BaseEvent {
    private static final String DOMAIN_TYPE = "REPORT";

    private ReportCreatedEvent(UUID reportId, Object payload) {
        super(DOMAIN_TYPE, reportId, payload);
    }

    public static ReportCreatedEvent of(ReportResult report, BookResult book) {
        return new ReportCreatedEvent(
                report.reportId(),
                new Payload(book.id(), book.title(), book.categoryName(), book.author(), book.description(),
                        report.userId(), report.reportId(), report.content(),
                        report.meetingId(), report.scheduleId(), report.createdAt()));
    }

    public record Payload(String bookId, String bookName, String bookCategory, String bookAuthors,
                          String bookDescription,
                          UUID userId, UUID reportId, String reportContent,
                          UUID meetingId, UUID meetingScheduleId, LocalDateTime createdAt) {
    }
}
