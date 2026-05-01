package com.pagely.reportservice.infrastructure.provider;

import com.pagely.reportservice.application.dto.result.BookResult;
import com.pagely.reportservice.application.port.out.BookProvider;
import com.pagely.reportservice.infrastructure.client.book.BookClient;
import com.pagely.reportservice.infrastructure.client.book.BookResponseDto;
import com.pagely.reportservice.infrastructure.client.book.exception.detail.NotFoundBookException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookProviderAdapter implements BookProvider {
    private final BookClient bookClient;

    @Override
    public BookResult getById(String bookId) {
        BookResponseDto book = bookClient.getBook(bookId);
        if (Objects.isNull(book) || Objects.isNull(book.data())) {
            throw new NotFoundBookException();
        }
        log.debug("도서 정보 조회 id: {}, title: {}", book.data().id(), book.data().title());
        return book.toResult();
    }
}
