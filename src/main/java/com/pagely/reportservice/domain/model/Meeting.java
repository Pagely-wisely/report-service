package com.pagely.reportservice.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting {

    @Column(name = "meeting_id")
    private UUID id;
    @Column(name = "meeting_schedule_id")
    private UUID scheduleId;

    public static Meeting of(UUID id, UUID scheduleId) {
        return new Meeting(id, scheduleId);
    }
}
