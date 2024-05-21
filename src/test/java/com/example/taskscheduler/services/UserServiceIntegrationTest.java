package com.example.taskscheduler.services;

import com.example.taskscheduler.models.User;
import com.example.taskscheduler.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void testRegisterUser() {
        User user = userService.registerUser("Jot", "jot.@example.com", "password");

        assertNotNull(user);
        assertEquals("Jot", user.getName());
        assertEquals("jot.@example.com", user.getEmail());
        assertTrue(userRepository.findByEmail("jot.@example.com").isPresent());
    }

    @Test
    void testLoginUser() {
        User user = new User();
        user.setName("Jol De");
        user.setEmail("jol.de@example.com");
        user.setPassword(passwordEncoder.encode("1234").getBytes());
        user = userRepository.save(user);

        User loggedInUser = userService.loginUser("jol.de@example.com", "1234");

        assertNotNull(loggedInUser);
        assertEquals(user.getUserId(), loggedInUser.getUserId());
    }

    @Test
    void testGetUserInfoById() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password".getBytes());
        user = userRepository.save(user);

        User foundUser = userService.getUserInfoById(user.getUserId());

        assertNotNull(foundUser);
        assertEquals(user.getUserId(), foundUser.getUserId());
    }

    @Test
    void testDeleteUserById() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password".getBytes());
        user = userRepository.save(user);

        userService.deleteUserById(user.getUserId());

        assertFalse(userRepository.findById(user.getUserId()).isPresent());
    }
}