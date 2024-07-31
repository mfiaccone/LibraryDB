package org.perscholas.librarydb.database.service;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.UserDAO;
import org.perscholas.librarydb.database.entity.User;
import org.perscholas.librarydb.form.CreateUserFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateUserFormBean form) {
        User user = new User();

        user.setEmail(form.getEmail());

        //encrypt the password before saving it to the database
        String encryptedPassword = passwordEncoder.encode(form.getPassword());

        user.setPassword(encryptedPassword);
        user.setName(form.getName());
        user.setCreateDate(new Date());

        // save the user to the database
        userDao.save(user);

        return user;
    }
}