package com.emincingoz.bookservice.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.emincingoz.bookservice.dto.AuthorDTO;
import com.emincingoz.bookservice.dto.BookCreateDTO;
import com.emincingoz.bookservice.dto.BookDTO;
import com.emincingoz.bookservice.dto.GenreDTO;
import com.emincingoz.bookservice.dto.InterpreterDTO;
import com.emincingoz.bookservice.dto.PublisherDTO;
import com.emincingoz.bookservice.exception.BookExceptionUtility;
import com.emincingoz.bookservice.mapper.AuthorMapper;
import com.emincingoz.bookservice.mapper.BookMapper;
import com.emincingoz.bookservice.mapper.GenreMapper;
import com.emincingoz.bookservice.mapper.InterpreterMapper;
import com.emincingoz.bookservice.mapper.PublisherMapper;
import com.emincingoz.bookservice.model.BindingType;
import com.emincingoz.bookservice.model.PaperType;
import com.emincingoz.bookservice.repository.BookRepository;
import com.emincingoz.bookservice.repository.entity.Book;
import com.emincingoz.bookservice.service.AuthorService;
import com.emincingoz.bookservice.service.BookService;
import com.emincingoz.bookservice.service.GenreService;
import com.emincingoz.bookservice.service.InterpreterService;
import com.emincingoz.bookservice.service.PublisherService;
import com.emincingoz.bookservice.util.BookServiceUtil;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Book service interface implementation class
 *
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Service
@Log4j2
public class BookServiceImpl implements BookService {
    /**
     * Used for pagination as default used page for get operation
     */
    private static final Integer DEFAULT_PAGE = 0;
    /**
     * Used for pagination as default used page size for get operation
     */
    private static final Integer DEFAULT_SIZE = 10;

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final InterpreterService interpreterService;
    private final PublisherService publisherService;

    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;
    private final InterpreterMapper interpreterMapper;
    private final PublisherMapper publisherMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, GenreService genreService, InterpreterService interpreterService, PublisherService publisherService, AuthorMapper authorMapper, GenreMapper genreMapper, InterpreterMapper interpreterMapper, PublisherMapper publisherMapper, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.interpreterService = interpreterService;
        this.publisherService = publisherService;
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
        this.interpreterMapper = interpreterMapper;
        this.publisherMapper = publisherMapper;
        this.bookMapper = bookMapper;
    }

    /**
     * Returns books as pages
     *
     * @param page
     * @param size
     * @return Page<BookDTO>
     */
    @Override
    public Page<BookDTO> getAllBooks(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page != null ? page : DEFAULT_PAGE, size != null ? size : DEFAULT_SIZE);
        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<Book> bookList = bookPage.getContent();
        if (CollectionUtils.isEmpty(bookList)) {
            log.warn("bookList is null or empty");
            throw BookExceptionUtility.dataNotFoundException(BookExceptionUtility.BOOK_NOT_FOUND);
        }
        List<BookDTO> bookDTOList = bookMapper.map2BookDTOList(bookList);
        return new PageImpl<>(bookDTOList, bookPage.getPageable(), bookPage.getTotalElements());
    }

    /**
     * Returns book by given isbn
     *
     * @param isbn
     * @return BookDTO
     */
    @Override
    public BookDTO getBookByIsbn(String isbn) {
        if (Strings.isNullOrEmpty(isbn)) {
            log.warn("isbn number is null");
            throw BookExceptionUtility.invalidRequestException(BookExceptionUtility.INVALID_PARAMETER);
        }
        Book book = bookRepository.findBookByIsbn(isbn).orElseThrow(() -> {
            log.warn("Book not found with given isbn number {}", isbn);
            throw BookExceptionUtility.dataNotFoundException(BookExceptionUtility.BOOK_NOT_FOUND);
        });
        return bookMapper.map2BookDTO(book);
    }

    /**
     * Returns book dto list by given author name
     *
     * @param author
     * @return List<BookDTO>
     */
    @Override
    public List<BookDTO> getBooksByAuthor(String author) {
        if (Strings.isNullOrEmpty(author)) {
            log.warn("Author is null");
            throw BookExceptionUtility.invalidRequestException(BookExceptionUtility.INVALID_PARAMETER);
        }
        author = BookServiceUtil.decodeInput(author);
        List<Book> bookList = bookRepository.findByAuthorList_Author(author);
        if (CollectionUtils.isEmpty(bookList)) {
            log.warn("Book not found with given author information {}", author);
            throw BookExceptionUtility.dataNotFoundException(BookExceptionUtility.BOOK_NOT_FOUND);
        }
        return bookMapper.map2BookDTOList(bookList);
    }

    /**
     * Returns book dto list by given author name list
     *
     * @param authorList
     * @return List<BookDTO>
     */
    @Override
    public List<BookDTO> getBooksByAuthors(List<String> authorList) {
        if (CollectionUtils.isEmpty(authorList)) {
            log.warn("authorList is null or empty");
            throw BookExceptionUtility.dataNotFoundException(BookExceptionUtility.INVALID_PARAMETER);
        }
        List<Book> bookList = bookRepository.findBooksByAuthorList_AuthorIn(authorList);
        if (CollectionUtils.isEmpty(bookList)) {
            log.warn("bookList is empty");
            throw BookExceptionUtility.dataNotFoundException(BookExceptionUtility.BOOK_NOT_FOUND);
        }
        return bookMapper.map2BookDTOList(bookList);
    }

    /**
     * Returns book dto list by given book publisher name
     *
     * @param publisher
     * @return List<BookDTO>
     */
    @Override
    public List<BookDTO> getBooksByPublisher(String publisher) {
        if (Strings.isNullOrEmpty(publisher)) {
            log.warn("publisher is null or empty");
            throw BookExceptionUtility.dataNotFoundException(BookExceptionUtility.INVALID_PARAMETER);
        }
        publisher = BookServiceUtil.decodeInput(publisher);
        List<Book> bookList = bookRepository.findBooksByPublisher_Name(publisher);
        if (CollectionUtils.isEmpty(bookList)) {
            log.warn("bookList is empty");
            throw BookExceptionUtility.dataNotFoundException(BookExceptionUtility.BOOK_NOT_FOUND);
        }
        return bookMapper.map2BookDTOList(bookList);
    }

    /**
     * Adds new book to BOOK table if book is not already added, author, publisher, genre, interpreter is already added
     *
     * @param bookCreateDTO
     * @return BookDTO
     */
    @Override
    public BookDTO addBook(BookCreateDTO bookCreateDTO) {
        if (bookCreateDTO == null) {
            log.warn("bookCreateDTO is null");
            throw BookExceptionUtility.invalidRequestException(BookExceptionUtility.BOOK_NOT_FOUND);
        }
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(bookCreateDTO.getIsbn());
        if (bookOptional.isPresent()) {
            log.warn("New books cannot be added. There is a book with the same isbn number. isbn: {}", bookOptional.get().getIsbn());
            throw BookExceptionUtility.invalidRequestException(BookExceptionUtility.BOOK_ALREADY_ADDED);
        }
        List<AuthorDTO> authorDTOList = authorService.getAuthorsByAuthorNameList(bookCreateDTO.getAuthorList());
        PublisherDTO publisherDTO = publisherService.getPublisherByName(bookCreateDTO.getPublisher());
        List<GenreDTO> genreDTOList = genreService.getGenresByGenreNameList(bookCreateDTO.getGenreList());
        List<InterpreterDTO> interpreterDTOList = interpreterService.getInterpretersByNameList(bookCreateDTO.getInterpreterList());

        if (!Arrays.stream(BindingType.values()).map(BindingType::getText).collect(Collectors.toList()).contains(bookCreateDTO.getBindingType())) {
            log.warn("String {}: not one of the values accepted for BindingType Enum Class", bookCreateDTO.getBindingType());
            throw BookExceptionUtility.invalidRequestException(BookExceptionUtility.INVALID_PARAMETER);
        }
        if (!Arrays.stream(PaperType.values()).map(PaperType::getText).collect(Collectors.toList()).contains(bookCreateDTO.getPaperType())) {
            log.warn("String {}: not one of the values accepted for PaperType Enum Class", bookCreateDTO.getPaperType());
            throw BookExceptionUtility.invalidRequestException(BookExceptionUtility.INVALID_PARAMETER);
        }

        Book book = bookMapper.map2Book(bookCreateDTO);
        book.setAuthorList(new HashSet<>(authorMapper.map2AuthorList(authorDTOList)));
        book.setPublisher(publisherMapper.map2Publisher(publisherDTO));
        book.setGenreList(new HashSet<>(genreMapper.map2GenreList(genreDTOList)));
        book.setInterpreterList(new HashSet<>(interpreterMapper.map2InterpreterList(interpreterDTOList)));

        return bookMapper.map2BookDTO(bookRepository.save(book));
    }
}
