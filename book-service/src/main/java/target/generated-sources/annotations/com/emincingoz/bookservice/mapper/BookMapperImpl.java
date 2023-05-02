package com.emincingoz.bookservice.mapper;

import com.emincingoz.bookservice.dto.BookCreateDTO;
import com.emincingoz.bookservice.dto.BookDTO;
import com.emincingoz.bookservice.model.BindingType;
import com.emincingoz.bookservice.model.PaperType;
import com.emincingoz.bookservice.repository.entity.Author;
import com.emincingoz.bookservice.repository.entity.Book;
import com.emincingoz.bookservice.repository.entity.Genre;
import com.emincingoz.bookservice.repository.entity.Interpreter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T10:45:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book map2Book(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.id( bookDTO.getId() );
        book.isbn( bookDTO.getIsbn() );
        book.title( bookDTO.getTitle() );
        Set<Author> set = bookDTO.getAuthorList();
        if ( set != null ) {
            book.authorList( new LinkedHashSet<Author>( set ) );
        }
        book.subTitle( bookDTO.getSubTitle() );
        book.originalName( bookDTO.getOriginalName() );
        Set<Interpreter> set1 = bookDTO.getInterpreterList();
        if ( set1 != null ) {
            book.interpreterList( new LinkedHashSet<Interpreter>( set1 ) );
        }
        book.subject( bookDTO.getSubject() );
        book.printLanguage( bookDTO.getPrintLanguage() );
        book.language( bookDTO.getLanguage() );
        book.numberOfPages( bookDTO.getNumberOfPages() );
        Set<Genre> set2 = bookDTO.getGenreList();
        if ( set2 != null ) {
            book.genreList( new LinkedHashSet<Genre>( set2 ) );
        }
        book.publisher( bookDTO.getPublisher() );
        book.publicationPlace( bookDTO.getPublicationPlace() );
        book.paperType( bookDTO.getPaperType() );
        book.bindingType( bookDTO.getBindingType() );
        book.releaseDate( bookDTO.getReleaseDate() );
        book.estimatedReadingTime( bookDTO.getEstimatedReadingTime() );
        book.bookHeight( bookDTO.getBookHeight() );
        book.bookWidth( bookDTO.getBookWidth() );

        return book.build();
    }

    @Override
    public Book map2Book(BookCreateDTO bookCreateDTO) {
        if ( bookCreateDTO == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.isbn( bookCreateDTO.getIsbn() );
        book.title( bookCreateDTO.getTitle() );
        book.subTitle( bookCreateDTO.getSubTitle() );
        book.originalName( bookCreateDTO.getOriginalName() );
        book.subject( bookCreateDTO.getSubject() );
        book.printLanguage( bookCreateDTO.getPrintLanguage() );
        book.language( bookCreateDTO.getLanguage() );
        book.numberOfPages( bookCreateDTO.getNumberOfPages() );
        book.publicationPlace( bookCreateDTO.getPublicationPlace() );
        if ( bookCreateDTO.getPaperType() != null ) {
            book.paperType( Enum.valueOf( PaperType.class, bookCreateDTO.getPaperType() ) );
        }
        if ( bookCreateDTO.getBindingType() != null ) {
            book.bindingType( Enum.valueOf( BindingType.class, bookCreateDTO.getBindingType() ) );
        }
        book.releaseDate( bookCreateDTO.getReleaseDate() );
        book.estimatedReadingTime( bookCreateDTO.getEstimatedReadingTime() );
        book.bookHeight( bookCreateDTO.getBookHeight() );
        book.bookWidth( bookCreateDTO.getBookWidth() );

        return book.build();
    }

    @Override
    public BookDTO map2BookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO.BookDTOBuilder bookDTO = BookDTO.builder();

        bookDTO.id( book.getId() );
        bookDTO.isbn( book.getIsbn() );
        bookDTO.title( book.getTitle() );
        Set<Author> set = book.getAuthorList();
        if ( set != null ) {
            bookDTO.authorList( new LinkedHashSet<Author>( set ) );
        }
        bookDTO.subTitle( book.getSubTitle() );
        bookDTO.originalName( book.getOriginalName() );
        Set<Interpreter> set1 = book.getInterpreterList();
        if ( set1 != null ) {
            bookDTO.interpreterList( new LinkedHashSet<Interpreter>( set1 ) );
        }
        bookDTO.subject( book.getSubject() );
        bookDTO.printLanguage( book.getPrintLanguage() );
        bookDTO.language( book.getLanguage() );
        bookDTO.numberOfPages( book.getNumberOfPages() );
        Set<Genre> set2 = book.getGenreList();
        if ( set2 != null ) {
            bookDTO.genreList( new LinkedHashSet<Genre>( set2 ) );
        }
        bookDTO.publisher( book.getPublisher() );
        bookDTO.publicationPlace( book.getPublicationPlace() );
        bookDTO.paperType( book.getPaperType() );
        bookDTO.bindingType( book.getBindingType() );
        bookDTO.releaseDate( book.getReleaseDate() );
        bookDTO.estimatedReadingTime( book.getEstimatedReadingTime() );
        bookDTO.bookHeight( book.getBookHeight() );
        bookDTO.bookWidth( book.getBookWidth() );

        return bookDTO.build();
    }

    @Override
    public List<Book> map2BookList(List<BookDTO> bookDTOList) {
        if ( bookDTOList == null ) {
            return null;
        }

        List<Book> list = new ArrayList<Book>( bookDTOList.size() );
        for ( BookDTO bookDTO : bookDTOList ) {
            list.add( map2Book( bookDTO ) );
        }

        return list;
    }

    @Override
    public List<BookDTO> map2BookDTOList(List<Book> bookList) {
        if ( bookList == null ) {
            return null;
        }

        List<BookDTO> list = new ArrayList<BookDTO>( bookList.size() );
        for ( Book book : bookList ) {
            list.add( map2BookDTO( book ) );
        }

        return list;
    }
}
