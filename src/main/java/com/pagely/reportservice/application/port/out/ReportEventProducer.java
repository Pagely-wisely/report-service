package com.pagely.reportservice.application.port.out;

import com.pagely.reportservice.domain.event.BaseEvent;

public interface ReportEventProducer {
    void publish(BaseEvent event);
}
