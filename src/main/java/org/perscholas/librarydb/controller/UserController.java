package org.perscholas.librarydb.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.BookDAO;
import org.perscholas.librarydb.database.dao.BorrowedBookDAO;
import org.perscholas.librarydb.database.dao.UserDAO;
import org.perscholas.librarydb.database.entity.Book;
import org.perscholas.librarydb.database.entity.BorrowedBook;
import org.perscholas.librarydb.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private BorrowedBookDAO borrowedBookDao;

    @Autowired
    private BookDAO bookDao;

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(required=false) Integer id, Principal principal) {
        ModelAndView response = new ModelAndView("user/detail");

        User user = userDao.findByEmailIgnoreCase(principal.getName());
        if (user == null) {
            // Handle the case where no user is found
            return new ModelAndView("redirect:/error");
        }
        response.addObject("user", user);

        return response;
    }

    @GetMapping("/bookshelf")
    public ModelAndView bookshelf(Principal principal, HttpSession session) {
        ModelAndView response = new ModelAndView("user/bookshelf");

        User user = userDao.findByEmailIgnoreCase(principal.getName());
        if (user == null) {
            // Handle the case where no user is found
            return new ModelAndView("redirect:/error");
        }

        session.setAttribute("userId", user.getId());
        log.info("Set userId in session: {}", user.getId());

        List<BorrowedBook> borrowedBooks = borrowedBookDao.findByUserId(user.getId());

        List<Book> books = new ArrayList<>();
        for (BorrowedBook borrowedBook : borrowedBooks) {
            Book book = bookDao.findByBookId(borrowedBook.getBookId());
            books.add(book);
        }

        response.addObject("user", user);
        response.addObject("borrowedBooks", borrowedBooks);
        response.addObject("books", books);

        return response;
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Integer borrowId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userDao.findById(userId);

        BorrowedBook borrowedBook = borrowedBookDao.findByBorrowId(borrowId);
        if (borrowedBook != null) {
            Book book = bookDao.findByBookId(borrowedBook.getBookId());
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookDao.save(book);
            borrowedBookDao.delete(borrowedBook);
        }
        return "redirect:/user/bookshelf?id=" + user.getId();
    }

    @PostMapping("/checkout")
    public String checkoutBook(@RequestParam Integer bookId, Principal principal) {
        if (principal == null) {
            // handle the case where the user is not authenticated
            return "redirect:/login";
        }

        String username = principal.getName();
        User user = userDao.findByEmailIgnoreCase(username);

        Book book = bookDao.findByBookId(bookId);

        if (book != null && book.getAvailableCopies() > 0) {
            BorrowedBook borrowedBook = new BorrowedBook();
            borrowedBook.setUserId(user.getId());
            borrowedBook.setBookId(bookId);
            borrowedBook.setBorrowDate(new Date());
            borrowedBook.setDueDate(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000));
            borrowedBookDao.save(borrowedBook);

            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookDao.save(book);
        }

        return "redirect:/user/bookshelf?id=" + user.getId();
    }


















//    @PostMapping("/checkout")
//    public String checkoutBook(@RequestParam Integer bookId, HttpSession session) {
//        System.out.println("Session attributes:");
//        java.util.Enumeration<String> attributeNames = session.getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            String attributeName = attributeNames.nextElement();
//            System.out.println(attributeName + ": " + session.getAttribute(attributeName));
//        }
//
//        // the problem is this below line, something wrong with the casting maybe?
//        Integer userId = (Integer) session.getAttribute("userId");
//        log.info("Retrieved userId from session: {}", userId);
//
//
//        System.out.println("User ID from session: " + userId);
//        User user = userDao.findById(userId);
//
//        Book book = bookDao.findByBookId(bookId);
//
//        if (book != null && book.getAvailableCopies() > 0) {
//            BorrowedBook borrowedBook = new BorrowedBook();
//            borrowedBook.setUserId(userId);
//            borrowedBook.setBookId(bookId);
//            borrowedBook.setBorrowDate(new Date());
//            borrowedBook.setDueDate(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000));
//            borrowedBookDao.save(borrowedBook);
//
//            book.setAvailableCopies(book.getAvailableCopies() - 1);
//            bookDao.save(book);
//
//        }
//        return "redirect:/user/bookshelf?id=" + user.getId();
//    }

}
