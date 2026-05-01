package com.pagely.reportservice.presentation.controller;

import com.pagely.common.auth.Role;
import com.pagely.common.auth.annotation.AuthRequired;
import com.pagely.common.auth.annotation.CurrentUserId;
import com.pagely.common.pagination.PageRequest;
import com.pagely.common.response.ApiResponse;
import com.pagely.reportservice.application.dto.query.GetReportsQuery;
import com.pagely.reportservice.application.dto.result.ReportResult;
import com.pagely.reportservice.application.service.ReportCommandService;
import com.pagely.reportservice.application.service.ReportQueryService;
import com.pagely.reportservice.domain.model.ReadScope;
import com.pagely.reportservice.presentation.dto.request.CreateReportRequestDto;
import com.pagely.reportservice.presentation.dto.response.CreateReportResponseDto;
import com.pagely.reportservice.presentation.dto.response.GetReportsResponseDto;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;

    @AuthRequired(role = {Role.MASTER, Role.CREATOR, Role.USER})
    @PostMapping
    ResponseEntity<ApiResponse> createReport(
            @CurrentUserId UUID userId,
            @Valid @RequestBody CreateReportRequestDto request
    ) {
        ReportResult report = reportCommandService.createReport(request.toCommand(userId));
        return ApiResponse.ok(CreateReportResponseDto.from(report));
    }

    @AuthRequired(role = {Role.MASTER, Role.CREATOR, Role.USER})
    @GetMapping
    public ResponseEntity<ApiResponse> getReports(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestParam(required = false) ReadScope readScope,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size).toPageable(sort);
        GetReportsQuery query = new GetReportsQuery(userId, readScope);

        Page<ReportResult> result = reportQueryService.getReports(query, pageable);
        
        return ApiResponse.ok(result, GetReportsResponseDto::from);
    }
}
