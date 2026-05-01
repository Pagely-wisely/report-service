package com.pagely.reportservice.infrastructure.provider;

import com.pagely.reportservice.application.port.out.MeetingProvider;
import com.pagely.reportservice.domain.repository.query.MeetingInfo;
import com.pagely.reportservice.infrastructure.client.meeting.MeetingAccessResponseDto;
import com.pagely.reportservice.infrastructure.client.meeting.MeetingClient;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingProviderAdapter implements MeetingProvider {
    private final MeetingClient meetingClient;

    @Override
    public List<MeetingInfo> getByUserId(UUID userId) {
        MeetingAccessResponseDto response = meetingClient.getByUserId(userId);

        if (Objects.isNull(response) || !response.success() || Objects.isNull(response.data())) {
            return List.of();
        }

        return response.data().meetings().stream()
                .map(m -> new MeetingInfo(m.meetingId(), m.scheduleIds()))
                .toList();
    }
}
