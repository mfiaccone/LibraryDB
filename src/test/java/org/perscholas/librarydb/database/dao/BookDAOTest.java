package org.perscholas.librarydb.database.dao;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.perscholas.librarydb.database.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDAOTest {

    @Autowired
    private BookDAO bookDAO;

    private static Book testBook;

    @Test
    @Order(1)
    public void testCreateBook() {
        testBook = new Book();
        testBook.setTitle("Test Book");
        testBook.setAuthor("Test Author");
        testBook.setIsbn("1234567890");
        testBook.setGenre("Test Genre");
        testBook.setAvailableCopies(5);

        testBook = bookDAO.save(testBook);

        assertNotNull(testBook.getBookId());
    }

    @Test
    @Order(2)
    public void testFindByBookId() {
        Book foundBook = bookDAO.findByBookId(testBook.getBookId());
        assertNotNull(foundBook);
        assertEquals(testBook.getTitle(), foundBook.getTitle());
    }

    @ParameterizedTest
    @CsvSource({"Test, 1", "Author, 1", "1234567890, 1", "NonExistent, 0"})
    @Order(3)
    public void testSearchBooks(String searchTerm, int expectedResultCount) {
        List<Book> results = bookDAO.searchBooks(searchTerm);
        assertEquals(expectedResultCount, results.size());
    }

    @Test
    @Order(4)
    public void testDeleteBook() {
        bookDAO.delete(testBook);
        Book deletedBook = bookDAO.findByBookId(testBook.getBookId());
        assertNull(deletedBook);
    }
}