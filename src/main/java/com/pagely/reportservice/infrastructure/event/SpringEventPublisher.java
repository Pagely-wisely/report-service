package com.pagely.reportservice.infrastructure.event;

import com.pagely.reportservice.domain.event.BaseEvent;
import com.pagely.reportservice.domain.event.ReportEvents;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEventPublisher implements ReportEvents {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void event(BaseEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
