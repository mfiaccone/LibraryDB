package org.perscholas.librarydb.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView response = new ModelAndView("index");

        return response;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView response = new ModelAndView("login");

        return response;
    }


}
