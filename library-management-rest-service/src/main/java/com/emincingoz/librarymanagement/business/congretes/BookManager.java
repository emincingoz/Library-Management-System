package com.emincingoz.librarymanagement.business.congretes;

import com.emincingoz.librarymanagement.business.abstracts.BookService;
import com.emincingoz.librarymanagement.business.constants.messages.BusinessMessages;
import com.emincingoz.librarymanagement.business.dtos.BookDTO;
import com.emincingoz.librarymanagement.business.requests.book.CreateBookRequest;
import com.emincingoz.librarymanagement.business.requests.book.UpdateBookRequest;
import com.emincingoz.librarymanagement.core.utilities.results.*;
import com.emincingoz.librarymanagement.repository.IBookRepository;
import com.emincingoz.librarymanagement.domain.models.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookManager implements BookService {

    private final IBookRepository bookRepository;
    private ModelMapper modelMapper;

    public BookManager(IBookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<BookDTO>> getAllBooks() {
        return new DataSuccessResult<>(bookRepository.getAll(), BusinessMessages.BOOK_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result getBookByIsbn(String isbn) {
        final Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(!book.isPresent())
            return new ErrorResult(BusinessMessages.BOOK_NOT_FOUND);

        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return new DataSuccessResult<>(bookDTO, BusinessMessages.BOOK_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result addNewBook(CreateBookRequest createBookRequest) {

        final Optional<Book> temp = bookRepository.findByIsbn(createBookRequest.getIsbn());
        if(temp.isPresent())
            return new ErrorResult(BusinessMessages.BOOK_ISBN_ALREADY_EXISTS);

        Book book = modelMapper.map(createBookRequest, Book.class);
        bookRepository.save(book);
        return new SuccessResult(BusinessMessages.BOOK_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result updateBook(String isbn, UpdateBookRequest updateBookRequest) {

        final Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (!book.isPresent())
            return new ErrorResult(BusinessMessages.BOOK_NOT_FOUND);

        book.get().setLanguage(updateBookRequest.getLanguage());
        book.get().setGenre(updateBookRequest.getGenre());
        book.get().setPublisher(updateBookRequest.getPublisher());
        book.get().setSubject(updateBookRequest.getSubject());
        book.get().setNumberOfPages(updateBookRequest.getNumberOfPages());
        book.get().setTitle(updateBookRequest.getTitle());
        return new SuccessResult(BusinessMessages.BOOK_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result deleteBookByIsbn(String isbn) {

        final Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (!book.isPresent())
            return new ErrorResult(BusinessMessages.BOOK_NOT_FOUND);
        return new SuccessResult(BusinessMessages.BOOK_DELETED_SUCCESSFULLY);
    }
}
