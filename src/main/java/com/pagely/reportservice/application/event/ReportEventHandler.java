package com.pagely.reportservice.application.event;

import com.pagely.reportservice.domain.event.BaseEvent;


public interface ReportEventHandler {
    void handleEvent(BaseEvent event);
}
