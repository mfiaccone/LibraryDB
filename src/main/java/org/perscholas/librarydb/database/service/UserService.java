package org.perscholas.librarydb.database.service;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.UserDAO;
import org.perscholas.librarydb.database.entity.User;
import org.perscholas.librarydb.form.CreateUserFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserDAO userDao;

    public User createUser(CreateUserFormBean form) {
        User user = new User();

        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setName(form.getName());
        user.setCreateDate(new Date());

        // save the user to the database
        userDao.save(user);

        return user;
    }
}