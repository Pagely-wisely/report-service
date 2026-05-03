package com.pagely.reportservice.application.service;

import com.pagely.reportservice.application.dto.query.GetReportsQuery;
import com.pagely.reportservice.application.dto.result.ReportResult;
import com.pagely.reportservice.application.port.out.MeetingProvider;
import com.pagely.reportservice.domain.model.ReadScope;
import com.pagely.reportservice.domain.model.Report;
import com.pagely.reportservice.domain.repository.query.MeetingInfo;
import com.pagely.reportservice.domain.repository.query.ReportQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportQueryService {

    private final ReportQueryRepository reportQueryRepository;
    private final MeetingProvider meetingProvider;

    public Page<ReportResult> getReports(GetReportsQuery query, Pageable pageable) {

        List<MeetingInfo> myMeetings = needsMeetingInfo(query.readScope())
                ? meetingProvider.getByUserId(query.userId())
                : List.of();

        log.debug("독후감 목록 조회 userId: {} readScope: {} 모임 수: {}",
                query.userId(), query.readScope(), myMeetings.size());

        Page<Report> reports = reportQueryRepository.findAllByCondition(
                query.userId(), query.readScope(), myMeetings, pageable);

        return reports.map(ReportResult::from);
    }

    private boolean needsMeetingInfo(ReadScope readScope) {
        return readScope == null || readScope == ReadScope.MEETING;
    }
}
