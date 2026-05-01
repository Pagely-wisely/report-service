package com.pagely.reportservice.infrastructure.client.book;

import com.pagely.reportservice.application.dto.result.BookResult;
import com.pagely.reportservice.infrastructure.client.book.exception.detail.NotFoundBookException;
import java.time.LocalDateTime;
import java.util.Objects;

public record BookResponseDto(
        boolean success,
        Data data
) {
    public BookResult toResult() {
        if (Objects.isNull(data)) {
            throw new NotFoundBookException();
        }
        return new BookResult(
                data.id,
                data.title,
                data.author,
                data.publisher,
                data.thumbnailUrl,
                data.description,
                data.publishedAt,
                data.categoryId,
                data.categoryName
        );
    }

    public record Data(
            String id,
            String title,
            String author,
            String publisher,
            String thumbnailUrl,
            String description,
            LocalDateTime publishedAt,
            Long categoryId,
            String categoryName
    ) {
    }
}