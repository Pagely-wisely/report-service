package com.pagely.reportservice.infrastructure.persistence;

import com.pagely.reportservice.domain.model.Report;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReportRepository extends JpaRepository<Report, UUID> {
}
