package com.emincingoz.bookservice.mapper;

import com.emincingoz.bookservice.dto.AuthorCreateDTO;
import com.emincingoz.bookservice.dto.AuthorDTO;
import com.emincingoz.bookservice.repository.entity.Author;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T10:45:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author map2Author(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( authorDTO.getId() );
        author.setAuthor( authorDTO.getAuthor() );

        return author;
    }

    @Override
    public Author map2Author(AuthorCreateDTO authorCreateDTO) {
        if ( authorCreateDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setAuthor( authorCreateDTO.getAuthor() );

        return author;
    }

    @Override
    public List<Author> map2AuthorList(List<AuthorDTO> authorDTOList) {
        if ( authorDTOList == null ) {
            return null;
        }

        List<Author> list = new ArrayList<Author>( authorDTOList.size() );
        for ( AuthorDTO authorDTO : authorDTOList ) {
            list.add( map2Author( authorDTO ) );
        }

        return list;
    }

    @Override
    public AuthorDTO map2AuthorDTO(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDTO.AuthorDTOBuilder authorDTO = AuthorDTO.builder();

        authorDTO.id( author.getId() );
        authorDTO.author( author.getAuthor() );

        return authorDTO.build();
    }

    @Override
    public List<AuthorDTO> map2AuthorDTOList(List<Author> authorList) {
        if ( authorList == null ) {
            return null;
        }

        List<AuthorDTO> list = new ArrayList<AuthorDTO>( authorList.size() );
        for ( Author author : authorList ) {
            list.add( map2AuthorDTO( author ) );
        }

        return list;
    }
}
