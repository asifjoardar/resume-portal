package com.asif.resumeportal.repository;

import com.asif.resumeportal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserName(String userName);
}