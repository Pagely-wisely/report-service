package com.pagely.reportservice.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {
    private UUID id;
    private String title;
    private String content;
    private ReadScope readScope;
    private String bookId;
    private UUID userId;
    private Meeting meeting;

    // 감사 필드
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
    private LocalDateTime deletedAt;
    private UUID deletedBy;
}
