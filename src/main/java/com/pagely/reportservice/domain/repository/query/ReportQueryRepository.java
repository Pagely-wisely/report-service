package com.pagely.reportservice.domain.repository.query;

import com.pagely.reportservice.domain.model.ReadScope;
import com.pagely.reportservice.domain.model.Report;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportQueryRepository {
    Page<Report> findAllByCondition(UUID userId,
                                    ReadScope readScope,
                                    List<MeetingInfo> myMeetings,
                                    Pageable pageable);
}
