package com.example.taskscheduler.services;

import com.example.taskscheduler.models.User;
import com.example.taskscheduler.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User is already exists");
        }

        byte[] hashedPassword = passwordEncoder.encode(password).getBytes();

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        return userRepository.save(newUser);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User was not found"));

        if (!passwordEncoder.matches(password, new String(user.getPassword()))) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    public User getUserInfoById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User was not found"));
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }
}