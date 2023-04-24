package com.emincingoz.bookservice.mapper;

import com.emincingoz.bookservice.dto.BookCreateDTO;
import com.emincingoz.bookservice.dto.BookDTO;
import com.emincingoz.bookservice.repository.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book map2Book(BookDTO bookDTO);

    @Mapping(target = "authorList", ignore = true)
    @Mapping(target = "interpreterList", ignore = true)
    @Mapping(target = "genreList", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    Book map2Book(BookCreateDTO bookCreateDTO);

    BookDTO map2BookDTO(Book book);

    List<Book> map2BookList(List<BookDTO> bookDTOList);

    List<BookDTO> map2BookDTOList(List<Book> bookList);

}
