package com.pagely.reportservice.application.port.out;

import java.util.List;
import java.util.UUID;

public interface MeetingProvider {
    List<UUID> getByUserId(UUID userId);
}
