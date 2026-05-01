package com.pagely.reportservice.domain.service;

import java.util.UUID;

public interface MeetingChecker {
    boolean hasMeetingId(UUID userId, UUID meetingId, UUID scheduleId);
}
