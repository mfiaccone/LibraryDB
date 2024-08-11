package org.perscholas.librarydb.database.dao;

import org.perscholas.librarydb.database.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowedBookDAO extends JpaRepository<BorrowedBook, Long> {

    List<BorrowedBook> findByUserId(Integer userId);

    BorrowedBook findByBorrowId(Integer borrowId);

    int countByUserId(Integer userId);

    @Query("select bb from BorrowedBook bb join fetch bb.book join fetch bb.user")
    List<BorrowedBook> findAllBorrowedBooks();
}
