package org.perscholas.librarydb.database.dao;

import org.perscholas.librarydb.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {


}
