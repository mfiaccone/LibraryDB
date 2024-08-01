package org.perscholas.librarydb.database.dao;

import org.perscholas.librarydb.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDAO extends JpaRepository<Book, Long> {


}
