package org.perscholas.librarydb.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.UserDAO;
import org.perscholas.librarydb.database.entity.Book;
import org.perscholas.librarydb.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDao;

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("user/detail");

        User user = userDao.findById(id);
        response.addObject("user", user);

        return response;
    }

    @GetMapping("/bookshelf")
    public ModelAndView bookshelf(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("user/bookshelf");

        User user = userDao.findById(id);
        response.addObject("user", user);

        return response;
    }

}
