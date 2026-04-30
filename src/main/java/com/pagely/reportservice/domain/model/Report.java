package com.pagely.reportservice.domain.model;

import com.pagely.common.entity.BaseEntity;
import com.pagely.reportservice.domain.exception.ReportErrorCode;
import com.pagely.reportservice.domain.exception.detail.InvalidMeetingException;
import com.pagely.reportservice.domain.exception.detail.InvalidUserException;
import com.pagely.reportservice.domain.exception.detail.MisMatchReadScopeException;
import com.pagely.reportservice.domain.exception.detail.NoPermissionMeetingException;
import com.pagely.reportservice.domain.service.MeetingChecker;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Table(name = "p_report")
@SQLRestriction("deleted_at IS NULL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "read_scope", nullable = false)
    private ReadScope readScope;

    @Column(name = "book_id", nullable = false, length = 20)
    private String bookId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Embedded
    private Meeting meeting;

    @Builder(access = AccessLevel.PRIVATE)
    private Report(String title, String content, ReadScope readScope,
                   String bookId, UUID userId, Meeting meeting) {
        this.title = title;
        this.content = content;
        this.readScope = readScope;
        this.bookId = bookId;
        this.userId = userId;
        this.meeting = meeting;
    }

    public static Report create(String title, String content, ReadScope readScope,
                                String bookId, UUID userId, UUID meetingId, UUID scheduleId,
                                MeetingChecker meetingChecker) {

        validateCreateReport(userId, readScope, meetingId, scheduleId, meetingChecker);

        return Report.builder()
                .title(title)
                .content(content)
                .readScope(readScope)
                .bookId(bookId)
                .userId(userId)
                .meeting(readScope == ReadScope.MEETING ? Meeting.of(meetingId, scheduleId) : null)
                .build();
    }

    private static void validateCreateReport(UUID userId, ReadScope readScope,
                                             UUID meetingId, UUID scheduleId,
                                             MeetingChecker meetingChecker) {
        if (Objects.isNull(userId)) {
            throw new InvalidUserException(ReportErrorCode.USER_NULL);
        }

        if (readScope == ReadScope.MEETING) {
            validateMeetingScope(userId, meetingId, scheduleId, meetingChecker);
        }
    }

    private static void validateMeetingScope(UUID userId, UUID meetingId,
                                             UUID scheduleId, MeetingChecker meetingChecker) {
        if (Objects.isNull(meetingId)) {
            throw new MisMatchReadScopeException();
        }
        if (Objects.isNull(scheduleId)) {
            throw new InvalidMeetingException();
        }
        if (!meetingChecker.hasMeetingId(userId, meetingId)) {
            throw new NoPermissionMeetingException();
        }
    }
}
