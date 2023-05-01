package com.emincingoz.bookservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.emincingoz.bookservice.exception.AuthorExceptionUtility;
import com.emincingoz.bookservice.repository.AuthorRepository;
import com.emincingoz.bookservice.service.AuthorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    private static final String URL = "/api/v1/book-service/author";

    @MockBean
    private AuthorService authorService;
    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    /*
    @GetMapping("/get-author-by-name/{authorName}")
    public ResponseEntity<AuthorDTO> getAuthorByAuthorName(@PathVariable("authorName") String authorName) {
        return new ResponseEntity<>(authorService.getAuthorByAuthorName(authorName), HttpStatus.OK);
    }
     */
    @Test
    void getAuthorByAuthorName_WhenAuthorNameNotGiven_ShouldThrowException() throws Exception {
        when(authorService.getAuthorByAuthorName(null)).thenThrow(AuthorExceptionUtility.invalidRequestException(AuthorExceptionUtility.INVALID_PARAMETER));
        String url = URL + "/get-author-by-name/{authorName}";
        MvcResult mvcResult = mockMvc.perform(get(url, "").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println("dasds: " + mvcResult.getResponse().getStatus());
    }

    @Test
    void addNewAuthor() {
    }
}