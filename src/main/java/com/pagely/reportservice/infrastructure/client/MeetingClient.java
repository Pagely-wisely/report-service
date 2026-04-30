package com.pagely.reportservice.infrastructure.client;

import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: 형태만 지정. 현재 아무 기능 없음
@FeignClient(
        name = "meeting"
)
public interface MeetingClient {
    // TODO: 형태만 지정. 현재 아무 기능 없음
    @GetMapping(value = "/internal/meeting")
    List<UUID> getByUserId(
            @RequestParam("userId") UUID userId);
}
