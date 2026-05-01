package com.pagely.reportservice.application.port.out;

import com.pagely.reportservice.application.dto.result.BookResult;

public interface BookProvider {
    BookResult getById(String bookId);
}
