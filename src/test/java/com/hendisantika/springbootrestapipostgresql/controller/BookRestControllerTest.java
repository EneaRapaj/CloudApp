package com.hendisantika.springbootrestapipostgresql.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hendisantika.springbootrestapipostgresql.entity.Book;
import com.hendisantika.springbootrestapipostgresql.repository.BookRepository;

class BookRestControllerTest {
    @InjectMocks
    private BookRestController bookRestController;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book(1L, "Book Name", "Description", "Tag1,Tag2");
        when(bookRepository.save(book)).thenReturn(book);

        ResponseEntity<?> responseEntity = bookRestController.addBook(book);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(book, responseEntity.getBody());
    }

    @Test
    void testGetAllBooks() {
        Collection<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book 1", "Description 1", "Tag1"));
        books.add(new Book(2L, "Book 2", "Description 2", "Tag2"));

        when(bookRepository.findAll()).thenReturn((List<Book>) books);

        ResponseEntity<Collection<Book>> responseEntity = bookRestController.getAllBooks();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(books, responseEntity.getBody());
    }

    @Test
    void testGetBookWithId() {
        Book book = new Book(1L, "Book Name", "Description", "Tag1,Tag2");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        ResponseEntity<Book> responseEntity = bookRestController.getBookWithId(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(book, responseEntity.getBody());
    }

    // Similar tests can be written for other methods

}