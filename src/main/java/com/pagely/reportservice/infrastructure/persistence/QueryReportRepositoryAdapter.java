package com.pagely.reportservice.infrastructure.persistence;

import com.pagely.reportservice.domain.model.QReport;
import com.pagely.reportservice.domain.model.ReadScope;
import com.pagely.reportservice.domain.model.Report;
import com.pagely.reportservice.domain.repository.query.MeetingInfo;
import com.pagely.reportservice.domain.repository.query.ReportQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueryReportRepositoryAdapter implements ReportQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Report> findAllByCondition(UUID userId,
                                           ReadScope readScope,
                                           List<MeetingInfo> myMeetings,
                                           Pageable pageable) {
        QReport report = QReport.report;
        BooleanExpression condition = buildCondition(report, userId, readScope, myMeetings);

        List<Report> content = queryFactory
                .selectFrom(report)
                .where(condition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(report.createdAt.desc())
                .fetch();

        Long total = queryFactory
                .select(report.count())
                .from(report)
                .where(condition)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    private BooleanExpression buildCondition(QReport report,
                                             UUID userId,
                                             ReadScope readScope,
                                             List<MeetingInfo> myMeetings) {
        // 필터 조건 없으면 볼 수 있는거 전부 조회
        if (readScope == null) {
            return publicCondition(report)
                    .or(myPrivateCondition(report, userId))
                    .or(meetingCondition(report, myMeetings));
        }

        return switch (readScope) {
            case PUBLIC -> publicCondition(report);
            case PRIVATE -> myPrivateCondition(report, userId);
            case MEETING -> meetingCondition(report, myMeetings);
        };
    }

    // PUBLIC 은 PUBLIC 설정만
    private BooleanExpression publicCondition(QReport report) {
        return report.readScope.eq(ReadScope.PUBLIC);
    }

    // PRIVATE 은 내가 쓴것만
    private BooleanExpression myPrivateCondition(QReport report, UUID userId) {
        return report.readScope.eq(ReadScope.PRIVATE)
                .and(report.userId.eq(userId));
    }

    // MEETING 은 내가 속한 모임+회차
    private BooleanExpression meetingCondition(QReport report,
                                               List<MeetingInfo> myMeetings) {
        if (myMeetings == null || myMeetings.isEmpty()) {
            return Expressions.FALSE;
        }

        BooleanExpression condition = null;
        for (MeetingInfo meeting : myMeetings) {
            BooleanExpression each = report.readScope.eq(ReadScope.MEETING)
                    .and(report.meeting.id.eq(meeting.meetingId()))
                    .and(report.meeting.scheduleId.in(meeting.scheduleIds()));

            condition = condition == null ? each : condition.or(each);
        }
        return condition;
    }
}
