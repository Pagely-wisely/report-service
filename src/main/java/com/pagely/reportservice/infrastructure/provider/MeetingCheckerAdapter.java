package com.pagely.reportservice.infrastructure.provider;

import com.pagely.reportservice.domain.service.MeetingChecker;
import com.pagely.reportservice.infrastructure.client.meeting.MeetingAccessResponseDto;
import com.pagely.reportservice.infrastructure.client.meeting.MeetingAccessResponseDto.Data.Meeting;
import com.pagely.reportservice.infrastructure.client.meeting.MeetingClient;
import com.pagely.reportservice.infrastructure.client.meeting.exception.detail.NotFountMeetingException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeetingCheckerAdapter implements MeetingChecker {
    private final MeetingClient meetingClient;

    @Override
    public boolean hasMeetingId(UUID userId, UUID meetingId, UUID scheduleId) {
        MeetingAccessResponseDto response = meetingClient.getByUserId(userId);

        if (Objects.isNull(response)
                || Objects.isNull(response.data())
                || Objects.isNull(response.data().meetings())) {
            throw new NotFountMeetingException();
        }

        List<Meeting> list = response.data().meetings();
        log.debug("모임 권한정보 획득. 참가 모임 갯수 : {}", list.size());
        return list.stream()
                .filter(m -> m.meetingId().equals(meetingId))
                .findFirst()
                .map(m -> m.scheduleIds().contains(scheduleId))
                .orElse(false);
    }
}
