package com.emincingoz.bookservice.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.emincingoz.bookservice.dto.AuthorCreateDTO;
import com.emincingoz.bookservice.dto.AuthorDTO;
import com.emincingoz.bookservice.exception.AuthorException;
import com.emincingoz.bookservice.mapper.AuthorMapper;
import com.emincingoz.bookservice.repository.AuthorRepository;
import com.emincingoz.bookservice.repository.entity.Author;
import com.emincingoz.bookservice.service.AuthorService;
import com.emincingoz.bookservice.util.BookServiceTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
class AuthorServiceImplTest {

    private AuthorService authorService;
    private AuthorRepository authorRepository;
    @Spy
    private AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    @BeforeEach
    void setUp() {
        authorRepository = mock(AuthorRepository.class);
        authorService = new AuthorServiceImpl(authorRepository, authorMapper);
    }

    @Test
    void getAuthorByAuthorName_WhenAuthorNameNotGiven_ShouldThrowException() {
        assertThatThrownBy(() -> authorService.getAuthorByAuthorName(null)).isInstanceOf(AuthorException.class);
    }

    @Test
    void getAuthorByAuthorName_WhenAuthorNameNotExists_ShouldThrowException() {
        when(authorRepository.findByAuthor(BookServiceTestUtil.INVALID_AUTHOR_NAME)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> authorService.getAuthorByAuthorName(BookServiceTestUtil.INVALID_AUTHOR_NAME)).isInstanceOf(AuthorException.class);
        verify(authorRepository).findByAuthor(BookServiceTestUtil.INVALID_AUTHOR_NAME);
    }

    @Test
    void getAuthorByAuthorName_WhenAuthorNameExists_ShouldReturnRecord() {
        Author author = BookServiceTestUtil.getAuthor();
        when(authorRepository.findByAuthor(BookServiceTestUtil.VALID_AUTHOR_NAME)).thenReturn(Optional.of(author));
        AuthorDTO authorDTO = authorService.getAuthorByAuthorName(BookServiceTestUtil.VALID_AUTHOR_NAME);
        assertThat(authorDTO).isNotNull();
        assertThat(authorDTO.getAuthor()).isEqualTo(author.getAuthor());
        verify(authorRepository).findByAuthor(BookServiceTestUtil.VALID_AUTHOR_NAME);
    }

    @Test
    void getAuthorsByAuthorNameList_WhenAuthorNameListNotGiven_ShouldThrowException() {
        assertThatThrownBy(() -> authorService.getAuthorsByAuthorNameList(null)).isInstanceOf(AuthorException.class);
    }

    @Test
    void getAuthorsByAuthorNameList_WhenAuthorNameListNotExists_ShouldThrowException() {
        List<String> authorNameList = new ArrayList<>(Collections.singleton("test"));
        assertThatThrownBy(() -> authorService.getAuthorsByAuthorNameList(authorNameList)).isInstanceOf(AuthorException.class);
    }

    @Test
    void getAuthorsByAuthorNameList_WhenAuthorNameListExists_ShouldReturnRecord() {
        Author author = BookServiceTestUtil.getAuthor();
        when(authorRepository.findAuthorsByAuthorIn(Collections.singletonList(BookServiceTestUtil.VALID_AUTHOR_NAME))).thenReturn(List.of(author));
        List<AuthorDTO> authorDTOList = authorService.getAuthorsByAuthorNameList(Collections.singletonList(BookServiceTestUtil.VALID_AUTHOR_NAME));
        assertThat(authorDTOList).isNotNull();
        assertThat(authorDTOList).isNotEmpty();
        assertThat(authorDTOList.get(0).getAuthor()).isEqualTo(author.getAuthor());
        verify(authorRepository).findAuthorsByAuthorIn(Collections.singletonList(BookServiceTestUtil.VALID_AUTHOR_NAME));
    }

    @Test
    void addAuthor_WhenAuthorCreateDTONotGiven_ShouldThrowException() {
        assertThatThrownBy(() -> authorService.addAuthor(null)).isInstanceOf(AuthorException.class);
    }

    @Test
    void addAuthor_WhenAuthorIsExists_ShouldThrowException() {
        Author author = BookServiceTestUtil.getAuthor();
        when(authorRepository.findByAuthor(author.getAuthor())).thenReturn(Optional.of(author));
        AuthorCreateDTO authorCreateDTO = new AuthorCreateDTO(author.getAuthor());
        assertThatThrownBy(() -> authorService.addAuthor(authorCreateDTO)).isInstanceOf(AuthorException.class);
    }

    @Test
    void addAuthor_WhenAuthorIsNotExists_ShouldReturnRecord() {
        when(authorRepository.findByAuthor(BookServiceTestUtil.VALID_AUTHOR_NAME)).thenReturn(Optional.empty());
        AuthorCreateDTO authorCreateDTO = new AuthorCreateDTO(BookServiceTestUtil.VALID_AUTHOR_NAME);
        Author author = authorMapper.map2Author(authorCreateDTO);
        // Use the below instead of "when" when using save method of repositories: // when(authorRepository.save(author)).thenReturn(author);
        doAnswer(invocation -> invocation.getArguments()[0]).when(authorRepository).save(Mockito.any(Author.class));
        AuthorDTO authorDTO = authorService.addAuthor(authorCreateDTO);
        assertThat(authorDTO).isNotNull();
        assertThat(authorDTO.getAuthor()).isEqualTo(BookServiceTestUtil.VALID_AUTHOR_NAME);
        verify(authorRepository).findByAuthor(BookServiceTestUtil.VALID_AUTHOR_NAME);
    }
}