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

import java.util.List;

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

    @GetMapping("/search")
    public ModelAndView search() {
        ModelAndView response = new ModelAndView("book/search");


        return response;
    }

    @GetMapping
    public ModelAndView bookSearchByTitle(@RequestParam(required = false) String bookSearch) {

        ModelAndView response = new ModelAndView("book/search");

        response.addObject("bookSearch", bookSearch);

        List<Book> book = bookDao.findByTitle(bookSearch);
        response.addObject("books", book);

        return response;

    }
}
