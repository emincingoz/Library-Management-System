package com.emincingoz.bookservice.mapper;

import java.util.List;

import com.emincingoz.bookservice.dto.AuthorCreateDTO;
import com.emincingoz.bookservice.dto.AuthorDTO;
import com.emincingoz.bookservice.repository.entity.Author;
import org.mapstruct.Mapper;

/**
 * Author mapper between dto and entity
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author map2Author(AuthorDTO authorDTO);

    Author map2Author(AuthorCreateDTO authorCreateDTO);
    List<Author> map2AuthorList(List<AuthorDTO> authorDTOList);
    AuthorDTO map2AuthorDTO(Author author);
    List<AuthorDTO> map2AuthorDTOList(List<Author> authorList);
}
