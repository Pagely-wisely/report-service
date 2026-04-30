package com.pagely.reportservice.infrastructure.provider;

import com.pagely.reportservice.application.port.out.MeetingProvider;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// TODO: 형태만 지정. 현재 아무 기능 없음
@Component
@RequiredArgsConstructor
public class MeetingProviderAdapter implements MeetingProvider {
//    private final MeetingClient meetingClient;

    // TODO: 형태만 지정. 현재 아무 기능 없음
    @Override
    public List<UUID> getByUserId(UUID userId) {
        return List.of();
    }
}
