package com.pagely.reportservice.application.dto.result;

import java.time.LocalDateTime;

public record BookResult(
        String id,
        String title,
        String author,
        String publisher,
        String thumbnailUrl,
        String description,
        LocalDateTime publishedAt,
        Long categoryId,
        String categoryName) {
}
