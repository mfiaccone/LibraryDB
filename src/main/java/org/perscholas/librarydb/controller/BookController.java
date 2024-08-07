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

        List<Book> books;

        // Trim search and genre, and set to null if they're empty or just whitespace
        search = (search != null) ? search.trim() : null;
        genre = (genre != null && !genre.equals("Or Select A Genre")) ? genre.trim() : null;

        if (search != null && genre != null) {

            books = bookDao.searchBooksByTitleAndGenre(search, genre);
        } else if (search != null) {

            books = bookDao.searchBooks(search);
        } else if (genre != null) {

            books = bookDao.searchByGenre(genre);
        } else {
            // Neither search nor valid genre is provided
            books = new ArrayList<>(); // this is to avoid book being null and cause null pointer
        }


        // this is for the lambda/stream/logging requirement
        books.stream().forEach(book -> {
            log.debug("Book: {}", book.getTitle());
        });

        response.addObject("books", books);
        response.addObject("searchTerm", search);
        response.addObject("selectedGenre", genre);

        return response;

    }
}
