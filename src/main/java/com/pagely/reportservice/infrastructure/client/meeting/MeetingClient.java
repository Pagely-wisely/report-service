package com.pagely.reportservice.infrastructure.client.meeting;

import com.pagely.reportservice.infrastructure.client.config.FeignClientConfig;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "meetingservice",
//        path = "${eureka.internal.meeting-service}",
        url = "${api.internal.meeting-service}",
        configuration = FeignClientConfig.class
)
public interface MeetingClient {

    @GetMapping(value = "/access/readable")
    MeetingAccessResponseDto getByUserId(
            @RequestParam(value = "userId") UUID userId);
}
