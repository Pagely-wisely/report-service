package com.pagely.reportservice.application.service;

import com.pagely.reportservice.application.dto.command.CreateReportCommand;
import com.pagely.reportservice.application.dto.result.BookResult;
import com.pagely.reportservice.application.dto.result.ReportResult;
import com.pagely.reportservice.application.port.out.BookProvider;
import com.pagely.reportservice.domain.event.ReportEvents;
import com.pagely.reportservice.domain.event.payload.ReportCreatedEvent;
import com.pagely.reportservice.domain.exception.detail.NotFoundBookException;
import com.pagely.reportservice.domain.model.Report;
import com.pagely.reportservice.domain.repository.ReportRepository;
import com.pagely.reportservice.domain.service.MeetingChecker;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReportCommandService {
    private final ReportRepository reportRepository;
    private final MeetingChecker meetingChecker;
    private final BookProvider bookProvider;
    private final ReportEvents reportEvents;

    public ReportResult createReport(CreateReportCommand command) {

        BookResult bookResult = bookProvider.getById(command.bookId());

        if (Objects.isNull(bookResult)) {
            throw new NotFoundBookException();
        }

        Report saved = reportRepository.save(
                Report.create(
                        command.title(), command.content(), command.readScope(),
                        command.bookId(), command.userId(),
                        command.meetingId(), command.scheduleId(), meetingChecker));

        ReportResult reportResult = ReportResult.from(saved);
        reportEvents.event(ReportCreatedEvent.of(reportResult, bookResult));

        log.info("독후감 생성");
        return reportResult;
    }
}
