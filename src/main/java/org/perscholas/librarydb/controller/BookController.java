package org.perscholas.librarydb.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.BookDAO;
import org.perscholas.librarydb.database.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookDAO bookDao;

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam Integer bookId) {
        ModelAndView response = new ModelAndView("book/detail");

        Book book = bookDao.findByBookId(bookId);
        response.addObject("book", book);

        return response;
    }
}
