package com.pagely.reportservice.application.service;

import com.pagely.reportservice.application.dto.command.CreateReportCommand;
import com.pagely.reportservice.application.dto.result.ReportResult;
import com.pagely.reportservice.domain.model.Report;
import com.pagely.reportservice.domain.repository.ReportRepository;
import com.pagely.reportservice.domain.service.MeetingChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportCommandService {
    private final ReportRepository reportRepository;
    private final MeetingChecker meetingChecker;

    public ReportResult createReport(CreateReportCommand command) {

        // TODO: feign client로 도서 정보 확인
        Report saved = reportRepository.save(
                Report.create(
                        command.title(), command.content(), command.readScope(),
                        command.bookId(), command.userId(),
                        command.meetingId(), command.scheduleId(), meetingChecker));
        // TODO: 독후감 생성 이벤트 발행
        return ReportResult.from(saved);
    }
}
