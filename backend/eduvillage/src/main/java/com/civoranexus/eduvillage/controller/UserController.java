package com.civoranexus.eduvillage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.civoranexus.eduvillage.dto.LoginResponse;
import com.civoranexus.eduvillage.entity.Course;
import com.civoranexus.eduvillage.entity.User;
import com.civoranexus.eduvillage.repository.UserRepository;
import com.civoranexus.eduvillage.service.UserService;
import org.springframework.security.core.Authentication;
import java.util.Set;



@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

 
    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    //private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository ) {
        
        this.userService = userService;
        this.userRepository = userRepository;
    }

    


    @GetMapping("/test")
    public String test() {
        return "User API is working";
    }

    
    @PostMapping("/register")
    public User register(@RequestBody User user) {
    return userService.registerUser(user);
}

    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
}


    
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    
    @PostMapping("/{userId}/enroll/{courseId}")
    public ResponseEntity<String> enrollCourse(
            @PathVariable Long userId,
            @PathVariable Long courseId) {

        userService.enrollCourse(userId, courseId);
        return ResponseEntity.ok("User enrolled successfully");
    }

    @GetMapping("/profile")
    public String profile() {
        return "JWT is working!";
    }

    @GetMapping("/me/courses")
    public Set<Course> getMyCourses(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getEnrolledCourses();
    }


}
