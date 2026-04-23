package com.pagely.reportservice.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/reports")
@RestController
public class ReportController {

    @GetMapping
    String hello() {
        return "HelloWorld!";
    }
}
