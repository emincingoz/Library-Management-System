package com.emincingoz.librarymanagement.business.abstracts;

import com.emincingoz.librarymanagement.business.dtos.BookDTO;
import com.emincingoz.librarymanagement.business.requests.book.CreateBookRequest;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();


    void addNewBook(CreateBookRequest createBookRequest);
}
