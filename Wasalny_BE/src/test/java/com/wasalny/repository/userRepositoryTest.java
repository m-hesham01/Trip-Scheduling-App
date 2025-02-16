package com.wasalny.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wasalny.entities.Station;
import com.wasalny.entities.User;

@DataJpaTest // configures an in memory h2 databse
public class userRepositoryTest {
    @Autowired
    private userRepository userRepo;

    @Test
    void testFindUserByName() {
        User user1 = new User( "testUser1", "123");
        User user2 = new User("testUser2", "456");
        userRepo.save(user1);
        userRepo.save(user2);

        List<User> result = userRepo.findByUsername("testUser1");

        assertFalse(result.isEmpty());
        assertEquals( result.get(0).getUsername(), "testUser1");
    }
}
