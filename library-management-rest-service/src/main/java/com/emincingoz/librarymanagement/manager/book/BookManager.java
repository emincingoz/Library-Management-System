package com.emincingoz.librarymanagement.manager.book;

import com.emincingoz.librarymanagement.domain.dtos.book.BookDTO;
import com.emincingoz.librarymanagement.domain.requests.book.CreateBookRequest;
import com.emincingoz.librarymanagement.domain.requests.book.UpdateBookRequest;
import com.emincingoz.librarymanagement.core.utilities.results.*;
import com.emincingoz.librarymanagement.repository.IBookRepository;
import com.emincingoz.librarymanagement.domain.models.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookManager implements IBookService {

    private final IBookRepository bookRepository;
    private ModelMapper modelMapper;

    public BookManager(IBookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<BookDTO>> getAllBooks() {
        return new DataSuccessResult<>(bookRepository.getAll(), BookMessageConstants.BOOK_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result getBookByIsbn(String isbn) {
        final Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(!book.isPresent())
            return new ErrorResult(BookMessageConstants.BOOK_NOT_FOUND);

        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return new DataSuccessResult<>(bookDTO, BookMessageConstants.BOOK_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result addNewBook(CreateBookRequest createBookRequest) {

        final Optional<Book> temp = bookRepository.findByIsbn(createBookRequest.getIsbn());
        if(temp.isPresent())
            return new ErrorResult(BookMessageConstants.BOOK_ISBN_ALREADY_EXISTS);

        Book book = modelMapper.map(createBookRequest, Book.class);
        bookRepository.save(book);
        return new SuccessResult(BookMessageConstants.BOOK_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result updateBook(String isbn, UpdateBookRequest updateBookRequest) {

        final Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (!book.isPresent())
            return new ErrorResult(BookMessageConstants.BOOK_NOT_FOUND);

        book.get().setLanguage(updateBookRequest.getLanguage());
        book.get().setGenre(updateBookRequest.getGenre());
        book.get().setPublisher(updateBookRequest.getPublisher());
        book.get().setSubject(updateBookRequest.getSubject());
        book.get().setNumberOfPages(updateBookRequest.getNumberOfPages());
        book.get().setTitle(updateBookRequest.getTitle());
        return new SuccessResult(BookMessageConstants.BOOK_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result deleteBookByIsbn(String isbn) {

        final Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (!book.isPresent())
            return new ErrorResult(BookMessageConstants.BOOK_NOT_FOUND);
        return new SuccessResult(BookMessageConstants.BOOK_DELETED_SUCCESSFULLY);
    }
}
