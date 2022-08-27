package com.emincingoz.librarymanagement.business.abstracts;

import com.emincingoz.librarymanagement.business.dtos.BookDTO;
import com.emincingoz.librarymanagement.business.requests.book.CreateBookRequest;
import com.emincingoz.librarymanagement.business.requests.book.UpdateBookRequest;
import com.emincingoz.librarymanagement.core.utilities.results.DataResult;
import com.emincingoz.librarymanagement.core.utilities.results.Result;

import java.util.List;

public interface BookService {

    DataResult<List<BookDTO>> getAllBooks();

    Result getBookByIsbn(String isbn);

    Result addNewBook(CreateBookRequest createBookRequest);

    Result updateBook(String isbn, UpdateBookRequest updateBookRequest);

    Result deleteBookByIsbn(String isbn);
}
