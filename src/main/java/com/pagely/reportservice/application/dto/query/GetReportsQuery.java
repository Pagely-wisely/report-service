package com.pagely.reportservice.application.dto.query;

import com.pagely.reportservice.domain.model.ReadScope;
import java.util.UUID;

public record GetReportsQuery(
        UUID userId,
        ReadScope readScope
) {
}
