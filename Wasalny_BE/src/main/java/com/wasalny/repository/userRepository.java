package com.wasalny.repository;

import com.wasalny.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface userRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u from User u WHERE u.username = :name")
    List<User> findByUsername(@Param("name") String name);
}
