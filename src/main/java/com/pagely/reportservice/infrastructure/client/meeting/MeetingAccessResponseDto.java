package com.pagely.reportservice.infrastructure.client.meeting;


import java.util.List;
import java.util.UUID;

public record MeetingAccessResponseDto(
        boolean success,
        Data data
) {
    public record Data(
            List<Meeting> meetings
    ) {
        public Data {
            meetings = meetings == null ? List.of() : List.copyOf(meetings);
        }

        public record Meeting(
                UUID meetingId,
                List<UUID> scheduleIds
        ) {
            public Meeting {
                scheduleIds = scheduleIds == null ? List.of() : List.copyOf(scheduleIds);
            }
        }
    }
}
