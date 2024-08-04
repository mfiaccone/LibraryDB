package org.perscholas.librarydb.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.BookDAO;
import org.perscholas.librarydb.database.dao.UserDAO;
import org.perscholas.librarydb.database.entity.Book;
import org.perscholas.librarydb.database.entity.User;
import org.perscholas.librarydb.database.service.BookService;
import org.perscholas.librarydb.form.CreateBookFormBean;
import org.perscholas.librarydb.form.EditUserFormBean;
import org.perscholas.librarydb.security.AuthenticatedUserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize(value = "hasAuthority('ADMIN')")
public class AdminController {


    @Autowired
    private UserDAO userDao;

    @Autowired
    private BookDAO bookDao;

    @Autowired
    private BookService bookService;

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView response = new ModelAndView("admin/dashboard");

        return response;
    }

    @GetMapping("/createBook")
    public ModelAndView createBookForm() {
        ModelAndView response = new ModelAndView("admin/create-book");
        response.addObject("form", new CreateBookFormBean());
        return response;
    }

    @PostMapping("/createBook")
    public ModelAndView createBookSubmit(@Valid @ModelAttribute("form") CreateBookFormBean form, BindingResult bindingResult, @RequestParam("cover") MultipartFile cover) {
        if (bindingResult.hasErrors()) {
            ModelAndView response = new ModelAndView("admin/create-book");
            response.addObject("form", form);
            return response;
        }

        bookService.createBook(form, cover);
        return new ModelAndView("redirect:/admin/dashboard");
    }

    @GetMapping("/editBook")
    public ModelAndView editBook() {
        ModelAndView response = new ModelAndView("admin/edit-book");


        return response;
    }

    @GetMapping("/editBookSearch")
    public ModelAndView editBookSearch(@RequestParam(required = false) String search) {
        ModelAndView response = new ModelAndView("admin/search-book");

        List<Book> books = bookDao.searchBooks(search);

        response.addObject("books", books);
        response.addObject("searchTerm", search);

        return response;
    }

    @GetMapping("/userSearch")
    public ModelAndView userSearch(@RequestParam(required = false) String search,@RequestParam(required = false) Integer searchId) {
        ModelAndView response = new ModelAndView("admin/search-user");

        List<User> users = userDao.searchUser(search, searchId);
        response.addObject("users", users);
        response.addObject("searchTerm", search);
        response.addObject("searchId", searchId);

        return response;
    }

    @GetMapping("/editUser")
    public ModelAndView editUser(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("admin/edit-user");

        User user = userDao.findById(id);

        if (user != null) {
            EditUserFormBean form = new EditUserFormBean();
            form.setId(user.getId());
            form.setEmail(user.getEmail());
            form.setName(user.getName());

            response.addObject("form", form);
        } else {
            log.warn("User with id {} not found", id);
        }
        return response;
    }

    @PostMapping("/editUser")
    public ModelAndView editUserSubmit(@Valid EditUserFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView("admin/edit-user");

        if (bindingResult.hasErrors()) {
            response.addObject("form", form);
            return response;
        }

        User user = userDao.findById(form.getId());
        if (user != null) {
            user.setEmail(form.getEmail());
            user.setName(form.getName());
            userDao.save(user);
            response.setViewName("redirect:/admin/userSearch");  // Redirect to user search page after successful edit
        }
        return response;
    }
}


