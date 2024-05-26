package com.example.taskscheduler.controllers.html;

import com.example.taskscheduler.models.User;
import com.example.taskscheduler.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserHtml {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAllUsers());
        return "user";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        User newUser = userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
        model.addAttribute("user", newUser);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/users/" + newUser.getUserId();
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userService.loginUser(email, password);
        model.addAttribute("user", user);
        return "redirect:/users/" + user.getUserId();
    }

    @GetMapping("/{userId}")
    public String getUserInfoById(@PathVariable Integer userId, Model model) {
        User user = userService.getUserInfoById(userId);
        model.addAttribute("user", user);
        return "userinfo";
    }

    @DeleteMapping("/{userId}")
    public String deleteUserById(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return "redirect:/users";
    }
}
