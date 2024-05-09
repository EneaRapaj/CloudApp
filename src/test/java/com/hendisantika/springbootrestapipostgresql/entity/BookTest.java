package com.hendisantika.springbootrestapipostgresql.entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hendisantika.springbootrestapipostgresql.entity.Book;
import com.hendisantika.springbootrestapipostgresql.repository.BookRepository;
import com.hendisantika.springbootrestapipostgresql.controller.BookRestController;

class BookTest {

    @InjectMocks
    private BookRestController bookRestController;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllBooks() {
        // Mocking book data
        Collection<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book 1", "Description 1", String.valueOf(new ArrayList<>())));
        books.add(new Book(2L, "Book 2", "Description 2", String.valueOf(new ArrayList<>())));

        // Mocking repository method
        when(bookRepository.findAll()).thenReturn((List<Book>) books);

        // Calling the method under test
        ResponseEntity<Collection<Book>> responseEntity = bookRestController.getAllBooks();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(books.size(), responseEntity.getBody().size());
        assertTrue(responseEntity.getBody().containsAll(books));
    }
}