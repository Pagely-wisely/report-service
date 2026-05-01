package com.pagely.reportservice.infrastructure.client.book;

import com.pagely.reportservice.infrastructure.client.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "bookservice",
//        path = "${eureka.internal.book-service}",
        url = "${api.internal.book-service}",
        configuration = FeignClientConfig.class
)
public interface BookClient {
    @GetMapping("/{bookId}")
    BookResponseDto getBook(
            @PathVariable("bookId") String bookId);
}
