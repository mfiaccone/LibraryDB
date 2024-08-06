package org.perscholas.librarydb.database.dao;

import org.perscholas.librarydb.database.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDAO extends JpaRepository<Review, Long>  {


}
