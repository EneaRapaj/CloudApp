package com.hendisantika.springbootrestapipostgresql.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class AuthorTest {
    @Test
    public void testGetterAndSetter() {
        // Create an instance of Author
        Author author = new Author(1L, "John Doe", "Bio");

        // Set values using setter methods
        author.setId(1L);
        author.setName("John Doe");
        author.setBio("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

        // Check if getter methods return the correct values
        assertEquals(1L, author.getId());
        assertEquals("John Doe", author.getName());
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", author.getBio());
    }

    @Test
    public void testConstructor() {
        // Create an instance of Author using constructor
        Author author = new Author("Jane Doe", "Suspendisse potenti. Donec commodo tellus vel est tincidunt.");

        // Check if constructor initializes fields correctly
        assertEquals("Jane Doe", author.getName());
        assertEquals("Suspendisse potenti. Donec commodo tellus vel est tincidunt.", author.getBio());
    }
}