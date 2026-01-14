package com.civoranexus.eduvillage.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.civoranexus.eduvillage.entity.User;
import com.civoranexus.eduvillage.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ TEST API
    @GetMapping("/test")
    public String test() {
        return "User API is working";
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    // ✅ GET ALL USERS
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ ENROLL COURSE
    @PostMapping("/{userId}/enroll/{courseId}")
    public ResponseEntity<String> enrollCourse(
            @PathVariable Long userId,
            @PathVariable Long courseId) {

        userService.enrollCourse(userId, courseId);
        return ResponseEntity.ok("User enrolled successfully");
    }
}
