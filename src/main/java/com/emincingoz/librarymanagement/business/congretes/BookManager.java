package com.emincingoz.librarymanagement.business.congretes;

import com.emincingoz.librarymanagement.business.abstracts.BookService;
import com.emincingoz.librarymanagement.business.dtos.BookDTO;
import com.emincingoz.librarymanagement.business.requests.book.CreateBookRequest;
import com.emincingoz.librarymanagement.dataAccess.abstracts.BookRepository;
import com.emincingoz.librarymanagement.entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookManager implements BookService {

    private final BookRepository bookRepository;
    private ModelMapper modelMapper;

    public BookManager(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDtos = new ArrayList<>();

        return modelMapper.map(books, bookDtos.getClass());
    }

    @Override
    public void addNewBook(CreateBookRequest createBookRequest) {
        Book book = modelMapper.map(createBookRequest, Book.class);
        bookRepository.save(book);
    }
}
