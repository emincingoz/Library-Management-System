package com.emincingoz.bookservice.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.emincingoz.bookservice.repository.entity.Author;
import com.emincingoz.bookservice.repository.entity.Book;

public class BookServiceTestUtil {
    public static final String INVALID_AUTHOR_NAME = ".";
    public static final Long VALID_ID = 1L;
    public static final String VALID_AUTHOR_NAME = "Test";

    public static Author getAuthor() {
        Author author = new Author();
        author.setId(BookServiceTestUtil.VALID_ID);
        author.setAuthor(VALID_AUTHOR_NAME);
        Book book = new Book();
        book.setAuthorList(Stream.of(author).collect(Collectors.toSet()));
        author.setBookList(Stream.of(book).collect(Collectors.toSet()));
        return author;
    }
}
