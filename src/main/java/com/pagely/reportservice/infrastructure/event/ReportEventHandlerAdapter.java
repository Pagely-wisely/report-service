package com.pagely.reportservice.infrastructure.event;

import com.pagely.reportservice.application.event.ReportEventHandler;
import com.pagely.reportservice.application.port.out.ReportEventProducer;
import com.pagely.reportservice.domain.event.BaseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ReportEventHandlerAdapter implements ReportEventHandler {
    private final ReportEventProducer reportEventProducer;

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEvent(BaseEvent event) {
        reportEventProducer.publish(event);
    }
}
