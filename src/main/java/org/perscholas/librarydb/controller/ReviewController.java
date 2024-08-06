package org.perscholas.librarydb.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.BookDAO;
import org.perscholas.librarydb.database.dao.ReviewDAO;
import org.perscholas.librarydb.database.dao.UserDAO;
import org.perscholas.librarydb.database.entity.Book;
import org.perscholas.librarydb.database.entity.Review;
import org.perscholas.librarydb.database.entity.User;
import org.perscholas.librarydb.form.CreateReviewFormBean;
import org.perscholas.librarydb.security.AuthenticatedUserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private BookDAO bookDao;

    @Autowired
    private ReviewDAO reviewDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    @GetMapping("/create")
    public ModelAndView review(@RequestParam Integer bookId){

        ModelAndView response = new ModelAndView("review/create");

        Book book = bookDao.findByBookId(bookId);
        CreateReviewFormBean form = new CreateReviewFormBean();
        form.setBookId(bookId);

        response.addObject("book", book);
        response.addObject("form", form);

        return response;
    }

    @PostMapping("/create")
    public ModelAndView submitReview(CreateReviewFormBean form, BindingResult bindingResult) {
        log.info("Received bookId: {}", form.getBookId());

        if (bindingResult.hasErrors()) {
            ModelAndView response = new ModelAndView("review/create");
            response.addObject("form", form);
            response.addObject("book", bookDao.findByBookId(form.getBookId()));
            return response;
        }

        Review review = new Review();
        review.setRating(form.getRating());
        review.setReviewText(form.getReviewText());

        review.setReviewDate(new Date());

        // Set the book
        Book book = bookDao.findByBookId(form.getBookId());
        review.setBook(book);

        // Set the user
        String username = authenticatedUserUtilities.getCurrentUsername();
        User user = userDao.findByEmailIgnoreCase(username);
        review.setUser(user);

        reviewDao.save(review);

        return new ModelAndView("redirect:/book/detail?bookId=" + form.getBookId());
    }

}
