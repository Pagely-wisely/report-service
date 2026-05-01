package com.pagely.reportservice.domain.repository;

import com.pagely.reportservice.domain.model.Report;

public interface ReportRepository {
    Report save(Report report);
}
