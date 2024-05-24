package com.example.taskscheduler.services;

import com.example.taskscheduler.models.User;
import com.example.taskscheduler.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void registerUserTest() {
        String name = "Nakano";
        String email = "nakano@yot.com";
        String password = "4284";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        String hashedPassword = "4284";
        when(passwordEncoder.encode(password)).thenReturn(hashedPassword);

        User savedUser = new User();
        savedUser.setName(name);
        savedUser.setEmail(email);
        savedUser.setPassword(hashedPassword);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User registeredUser = userService.registerUser(name, email, password);

        assertNotNull(registeredUser);
        assertEquals(name, registeredUser.getName());
        assertEquals(email, registeredUser.getEmail());
        assertEquals(hashedPassword, new String(registeredUser.getPassword()));
        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUserFailureTest() {
        String name = "Nakano";
        String email = "nakano@yot.com";
        String password = "4284";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> userService.registerUser(name, email, password));
        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void loginUserTest() {
        String email = "nakano@yot.com";
        String password = "4284";

        User user = new User();
        user.setEmail(email);
        user.setPassword("4284");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, new String(user.getPassword()))).thenReturn(true);

        User loggedInUser = userService.loginUser(email, password);

        assertEquals(user, loggedInUser);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void loginUserFailureTest() {
        String email = "nakano@yot.com";
        String password = "4284";

        User user = new User();
        user.setEmail(email);
        user.setPassword("428");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, new String(user.getPassword()))).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.loginUser(email, password));
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getUserInfoByIdTest() {
        Integer userId = 1;
        User user = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserInfoById(userId);

        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserInfoByIdFailureTest() {
        Integer userId = 1;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserInfoById(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void deleteUserByIdTest() {
        Integer userId = 1;

        userService.deleteUserById(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}