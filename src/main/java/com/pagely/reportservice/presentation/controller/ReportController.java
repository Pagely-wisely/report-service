package com.pagely.reportservice.presentation.controller;

import com.pagely.common.auth.Role;
import com.pagely.common.auth.annotation.AuthRequired;
import com.pagely.common.auth.annotation.CurrentUserId;
import com.pagely.common.response.ApiResponse;
import com.pagely.reportservice.application.dto.result.ReportResult;
import com.pagely.reportservice.application.service.ReportCommandService;
import com.pagely.reportservice.presentation.dto.request.CreateReportRequestDto;
import com.pagely.reportservice.presentation.dto.response.CreateReportResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportCommandService reportCommandService;

    @AuthRequired(role = {Role.MASTER, Role.CREATOR, Role.USER})
    @PostMapping
    ResponseEntity<ApiResponse> createReport(
            @CurrentUserId UUID userId,
            @RequestBody CreateReportRequestDto request
    ) {
        ReportResult report = reportCommandService.createReport(request.toCommand(userId));
        return ApiResponse.ok(CreateReportResponseDto.from(report));
    }
}
