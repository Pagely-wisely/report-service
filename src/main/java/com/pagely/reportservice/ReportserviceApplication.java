package com.pagely.reportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ReportserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportserviceApplication.class, args);
    }

}
