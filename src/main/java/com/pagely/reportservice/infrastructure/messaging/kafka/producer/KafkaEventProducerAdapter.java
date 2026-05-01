package com.pagely.reportservice.infrastructure.messaging.kafka.producer;

import com.pagely.reportservice.application.port.out.ReportEventProducer;
import com.pagely.reportservice.domain.event.BaseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventProducerAdapter implements ReportEventProducer {
    private static final String REPORT_CREATED_TYPE = "ReportCreatedEvent";
    private static final String REPORT_CREATED_TOPIC = "report-created";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(BaseEvent event) {
        String topic = getTopicName(event);
        publish(topic, event.getDomainId(), event);
    }

    private void publish(String topic, String key, Object event) {
        kafkaTemplate.send(topic, key, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Kafka 발행 실패 topic: {} key: {}", topic, key, ex);
                    } else {
                        log.info("Kafka 발행 성공 topic: {} key: {} offset: {}",
                                topic, key, result.getRecordMetadata().offset());
                    }
                });
    }

    private String getTopicName(BaseEvent event) {
        return switch (event.getEventType()) {
            case REPORT_CREATED_TYPE -> REPORT_CREATED_TOPIC;
            default -> throw new IllegalArgumentException("정의되지 않은 이벤트 타입입니다. eventType: " + event.getEventType());
        };
    }
}
