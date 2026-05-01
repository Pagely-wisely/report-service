package com.pagely.reportservice.infrastructure.client.book.exception.detail;

import com.pagely.reportservice.infrastructure.client.book.exception.BookErrorCode;
import com.pagely.reportservice.infrastructure.client.book.exception.BookException;

public class NotFoundBookException extends BookException {
    public NotFoundBookException() {
        super(BookErrorCode.BOOK_NOT_FOUND);
    }
}
