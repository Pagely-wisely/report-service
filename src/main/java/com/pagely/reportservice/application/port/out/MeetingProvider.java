package com.pagely.reportservice.application.port.out;

import com.pagely.reportservice.domain.repository.query.MeetingInfo;
import java.util.List;
import java.util.UUID;

public interface MeetingProvider {
    List<MeetingInfo> getByUserId(UUID userId);
}
