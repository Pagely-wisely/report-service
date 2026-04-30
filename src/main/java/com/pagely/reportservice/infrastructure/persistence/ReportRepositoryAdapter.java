package com.pagely.reportservice.infrastructure.persistence;

import com.pagely.reportservice.domain.model.Report;
import com.pagely.reportservice.domain.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReportRepositoryAdapter implements ReportRepository {
    private final JpaReportRepository jpaReportRepository;

    @Override
    public Report save(Report report) {
        return jpaReportRepository.save(report);
    }
}
