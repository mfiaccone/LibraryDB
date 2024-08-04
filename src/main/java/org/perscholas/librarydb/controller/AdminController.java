package org.perscholas.librarydb.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.UserDAO;
import org.perscholas.librarydb.database.entity.User;
import org.perscholas.librarydb.form.EditUserFormBean;
import org.perscholas.librarydb.security.AuthenticatedUserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize(value = "hasAuthority('ADMIN')")
public class AdminController {


    @Autowired
    private UserDAO userDao;

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView response = new ModelAndView("admin/dashboard");

        return response;
    }

    @GetMapping
    public ModelAndView editBook() {
        ModelAndView response = new ModelAndView("admin/edit-book");
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


