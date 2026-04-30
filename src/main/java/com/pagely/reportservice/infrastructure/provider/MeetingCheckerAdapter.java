package com.pagely.reportservice.infrastructure.provider;

import com.pagely.reportservice.domain.service.MeetingChecker;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// TODO: 형태만 지정. 현재 아무 기능 없음
@Component
@RequiredArgsConstructor
public class MeetingCheckerAdapter implements MeetingChecker {
//    private final MeetingClient meetingClient;

    // TODO: 형태만 지정. 현재 아무 기능 없음
    @Override
    public boolean hasMeetingId(UUID userId, UUID meetingId) {
        return true;
//        List<UUID> meetingIds = meetingClient.getByUserId(userId);
//        return meetingIds.contains(meetingId);
    }
}
