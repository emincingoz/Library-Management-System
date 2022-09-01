package com.emincingoz.librarymanagement.manager.book;

import com.emincingoz.librarymanagement.core.utilities.results.DataResult;
import com.emincingoz.librarymanagement.core.utilities.results.Result;
import com.emincingoz.librarymanagement.domain.dtos.book.BookDTO;
import com.emincingoz.librarymanagement.domain.requests.book.CreateBookRequest;
import com.emincingoz.librarymanagement.domain.requests.book.UpdateBookRequest;

import java.util.List;

public interface IBookService {

    DataResult<List<BookDTO>> getAllBooks();

    Result getBookByIsbn(String isbn);

    Result addNewBook(CreateBookRequest createBookRequest);

    Result updateBook(String isbn, UpdateBookRequest updateBookRequest);

    Result deleteBookByIsbn(String isbn);
}
