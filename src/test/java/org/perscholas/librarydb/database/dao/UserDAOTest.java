package org.perscholas.librarydb.database.dao;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.perscholas.librarydb.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    private static User testUser;

    @Test
    @Order(1)
    public void testCreateUser() {
        testUser = new User();
        testUser.setName("Test User");
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("password");
        testUser.setCreateDate(new Date());

        testUser = userDAO.save(testUser);

        assertNotNull(testUser.getId());
    }

    @Test
    @Order(2)
    public void testFindByEmailIgnoreCase() {
        User foundUser = userDAO.findByEmailIgnoreCase("testuser@example.com");
        assertNotNull(foundUser);
        assertEquals(testUser.getId(), foundUser.getId());
    }

    @Test
    @Order(3)
    public void testFindById() {
        User foundUser = userDAO.findById(testUser.getId());
        assertNotNull(foundUser);
        assertEquals(testUser.getEmail(), foundUser.getEmail());
    }

    @Test
    @Order(4)
    public void testDeleteUser() {
        userDAO.delete(testUser);
        User deletedUser = userDAO.findById(testUser.getId());
        assertNull(deletedUser);
    }
}