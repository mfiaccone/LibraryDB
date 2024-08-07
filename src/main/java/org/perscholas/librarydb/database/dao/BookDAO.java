package org.perscholas.librarydb.database.dao;

import org.perscholas.librarydb.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Long> {

    Book findByBookId (Integer bookId);

//    @Query("select b from Book b where b.title like concat('%', :title, '%')")
//    List<Book> findByTitle (String title);
//
//    @Query("select b from Book b where b.author like concat('%', :author, '%')")
//    List<Book> findByAuthor(String author);
//
//    @Query("select b from Book b where b.isbn = :isbn")
//    List<Book> findByIsbn(String isbn);

    @Query("select b from Book b where " +
            "b.title like concat('%', :search, '%') or " +
            "b.isbn = :search or " +
            "b.author like concat('%', :search, '%')")
    List<Book> searchBooks(String search);

    @Query("select b from Book b where b.genre = :search")
    List<Book> searchByGenre(String search);

    @Query("select b from Book b where " +
            "(b.title like concat('%', :search, '%') or " +
            "b.isbn = :search or " +
            "b.author like concat('%', :search, '%')) and " +
            "b.genre = :genre")
    List<Book> searchBooksByTitleAndGenre(String search, String genre);
}
