package org.perscholas.librarydb.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.UserDAO;
import org.perscholas.librarydb.database.entity.User;
import org.perscholas.librarydb.database.service.UserService;
import org.perscholas.librarydb.form.CreateUserFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserService userService;


    @GetMapping("/create-user")
    public ModelAndView createUser() {
        ModelAndView response = new ModelAndView("auth/create-user");

        return response;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView response = new ModelAndView("login");

        return response;
    }

    @PostMapping("/create-user")
    public ModelAndView createUserSubmit(@Valid CreateUserFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView("auth/create-user");

        if (form.getEmail() != null ) {
            User u = userDao.findByEmailIgnoreCase(form.getEmail());
            if (u != null) {
                // Email already exists in the database
                bindingResult.rejectValue("email", "email", "This email is already in use.");
            }
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
            }

            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
        } else {
            // there were no errors so we can create the new user in the database
            userService.createUser(form);

        }

        return response;
    }


}