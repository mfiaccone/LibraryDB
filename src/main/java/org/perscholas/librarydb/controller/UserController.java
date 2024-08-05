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
    public ModelAndView detail(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("user/detail");

        User user = userDao.findById(id);
        response.addObject("user", user);

        return response;
    }

    @GetMapping("/bookshelf")
    public ModelAndView bookshelf(@RequestParam Integer id, HttpSession session) {
        ModelAndView response = new ModelAndView("user/bookshelf");

        User user = userDao.findById(id);
        session.setAttribute("userId", user.getId());

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

//    @GetMapping("/bookshelf")
//    public ModelAndView bookshelf(Principal principal) {
//        ModelAndView response = new ModelAndView("user/bookshelf");
//
//        // Get the currently logged-in user
//        String email = principal.getName();
//        User user = userDao.findByEmailIgnoreCase(email);
//
//        // Fetch borrowed books for the user
//        List<BorrowedBook> borrowedBooks = borrowedBookDao.findByUserId(user.getId());
//
//        // Fetch full book details for each borrowed book
//        List<Book> books = new ArrayList<>();
//        for (BorrowedBook borrowedBook : borrowedBooks) {
//            Book book = bookDao.findByBookId(borrowedBook.getBookId());
//            books.add(book);
//        }
//
//        response.addObject("user", user);
//        response.addObject("borrowedBooks", borrowedBooks);
//        response.addObject("books", books);
//
//        return response;
//    }
}
