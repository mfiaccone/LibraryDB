package org.perscholas.librarydb.database.dao;

import org.perscholas.librarydb.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Long> {

    Book findByBookId (Integer bookId);

    @Query("select b from Book b where " +
            "b.title like concat('%', :search, '%') or " +
            "b.isbn = :search or " +
            "b.author like concat('%', :search, '%')")
    List<Book> searchBooks(String search);

    @Query(value = "SELECT * FROM book WHERE genre = :search", nativeQuery = true)
    List<Book> searchByGenre(String search);

    @Query("select b from Book b where " +
            "(b.title like concat('%', :search, '%') or " +
            "b.isbn = :search or " +
            "b.author like concat('%', :search, '%')) and " +
            "b.genre = :genre")
    List<Book> searchBooksByTitleAndGenre(String search, String genre);
}
