package com.civoranexus.eduvillage.controller;

import org.springframework.web.bind.annotation.*;
import com.civoranexus.eduvillage.entity.User;
import com.civoranexus.eduvillage.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/test")
    public String test() {
        return "User API is working";
    }
}
