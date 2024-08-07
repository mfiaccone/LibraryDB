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

import java.util.ArrayList;
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
        if (book != null && book.getReviews() != null) {
            book.getReviews().size(); // initialization of the reviews bc they're lazy
        }
        response.addObject("book", book);

        return response;
    }

    @GetMapping("/search")
    public ModelAndView search() {
        ModelAndView response = new ModelAndView("book/search");


        return response;
    }

    @GetMapping
    public ModelAndView bookSearch(@RequestParam(required = false) String search, @RequestParam(required = false) String genre) {

        ModelAndView response = new ModelAndView("book/search");

//        List<Book> books = bookDao.searchBooks(search);

        List<Book> books;
        if (search != null && !search.isEmpty() && genre != null && !genre.isEmpty()) {
            books = bookDao.searchBooksByTitleAndGenre(search, genre);
        } else if (search != null && !search.isEmpty()) {
            books = bookDao.searchBooks(search);
        } else if (genre != null && !genre.isEmpty()) {
            books = bookDao.searchByGenre(genre);
        } else {
            books = new ArrayList<>(); // to make sure books is never null and avoid null pointer
        }

        response.addObject("books", books);
        response.addObject("searchTerm", search);
        response.addObject("selectedGenre", genre);

        return response;

    }
}
