package com.emincingoz.librarymanagement.repository;

import com.emincingoz.librarymanagement.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
