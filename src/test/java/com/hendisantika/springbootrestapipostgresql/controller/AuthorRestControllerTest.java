package com.hendisantika.springbootrestapipostgresql.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hendisantika.springbootrestapipostgresql.entity.Author;
import com.hendisantika.springbootrestapipostgresql.repository.AuthorRepository;

class AuthorRestControllerTest {

    @InjectMocks
    private AuthorRestController authorRestController;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "John Doe", "Bio"));
        authors.add(new Author(2L, "Jane Doe", "Bio"));

        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorRestController.getAllAuthors();

        assertEquals(authors.size(), result.size());
        assertEquals(authors.get(0), result.get(0));
        assertEquals(authors.get(1), result.get(1));
    }

    @Test
    void testGetAuthorById() {
        Author author = new Author(1L, "John Doe", "Bio");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        ResponseEntity<Author> responseEntity = authorRestController.getAuthorById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(author, responseEntity.getBody());
    }

    @Test
    void testGetAuthorById_NotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Author> responseEntity = authorRestController.getAuthorById(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testCreateAuthor() {
        Author author = new Author(1L, "John Doe", "Bio");

        when(authorRepository.save(author)).thenReturn(author);

        ResponseEntity<Author> responseEntity = authorRestController.createAuthor(author);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(author, responseEntity.getBody());
    }

    // Similar tests can be written for updateAuthor and deleteAuthor methods
}
