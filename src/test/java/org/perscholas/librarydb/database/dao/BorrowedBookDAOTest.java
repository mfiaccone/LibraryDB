package org.perscholas.librarydb.database.dao;

import org.junit.jupiter.api.*;
import org.perscholas.librarydb.database.entity.Book;
import org.perscholas.librarydb.database.entity.BorrowedBook;
import org.perscholas.librarydb.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BorrowedBookDAOTest {

    @Autowired
    private BorrowedBookDAO borrowedBookDAO;

    private static BorrowedBook testBorrowedBook;
    private static User testUser;
    private static Book testBook;

    @BeforeAll
    public static void setUp(@Autowired UserDAO userDAO, @Autowired BookDAO bookDAO) {
        testUser = new User();
        testUser.setName("Test User");
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("password");
        testUser.setCreateDate(new Date());
        testUser = userDAO.save(testUser);

        testBook = new Book();
        testBook.setTitle("Test Book");
        testBook.setAuthor("Test Author");
        testBook.setIsbn("1234567890");
        testBook.setGenre("Test Genre");
        testBook.setAvailableCopies(5);
        testBook = bookDAO.save(testBook);
    }

    @Test
    @Order(1)
    public void testCreateBorrowedBook() {
        testBorrowedBook = new BorrowedBook();
        testBorrowedBook.setUser(testUser);
        testBorrowedBook.setBook(testBook);
        testBorrowedBook.setBorrowDate(new Date());
        testBorrowedBook.setDueDate(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000));

        testBorrowedBook = borrowedBookDAO.save(testBorrowedBook);

        assertNotNull(testBorrowedBook.getBorrowId());
    }

    @Test
    @Order(2)
    public void testFindByUserId() {
        List<BorrowedBook> borrowedBooks = borrowedBookDAO.findByUserId(testUser.getId());
        assertFalse(borrowedBooks.isEmpty());
        assertEquals(testBorrowedBook.getBorrowId(), borrowedBooks.get(0).getBorrowId());
    }

    @Test
    @Order(3)
    public void testCountByUserId() {
        int count = borrowedBookDAO.countByUserId(testUser.getId());
        assertEquals(1, count);
    }

    @Test
    @Order(4)
    public void testDeleteBorrowedBook() {
        borrowedBookDAO.delete(testBorrowedBook);
        BorrowedBook deletedBorrowedBook = borrowedBookDAO.findByBorrowId(testBorrowedBook.getBorrowId());
        assertNull(deletedBorrowedBook);
    }

    @AfterAll
    public static void tearDown(@Autowired UserDAO userDAO, @Autowired BookDAO bookDAO) {
        userDAO.delete(testUser);
        bookDAO.delete(testBook);
    }
}