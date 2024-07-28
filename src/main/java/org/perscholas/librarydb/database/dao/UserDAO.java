package org.perscholas.librarydb.database.dao;

import org.perscholas.librarydb.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {

    User findByEmailIgnoreCase (String email);


}