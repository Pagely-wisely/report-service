package com.pagely.reportservice.domain.repository.query;

import java.util.List;
import java.util.UUID;

public record MeetingInfo(
        UUID meetingId,
        List<UUID> scheduleIds
) {
}
