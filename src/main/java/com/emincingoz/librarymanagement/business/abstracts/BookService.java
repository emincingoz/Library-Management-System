package com.emincingoz.librarymanagement.business.abstracts;

import com.emincingoz.librarymanagement.dataAccess.abstracts.BookRepository;
import com.emincingoz.librarymanagement.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addNewBook(Book book) {
        return bookRepository.save(book);
    }
}
