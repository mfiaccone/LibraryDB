package org.perscholas.librarydb.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "borrowed_books")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private Integer borrowId;

    @Column(name = "user_id", insertable=false, updatable=false, nullable=false)
    private Integer userId;

    @Column(name = "book_id", insertable=false, updatable=false)
    private Integer bookId;

    @Column(name = "borrow_date")
    @Temporal(TemporalType.DATE)
    private Date borrowDate;

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
