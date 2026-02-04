package com.civoranexus.eduvillage.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.civoranexus.eduvillage.config.JwtService;
import com.civoranexus.eduvillage.dto.LoginResponse;
import com.civoranexus.eduvillage.entity.Course;
import com.civoranexus.eduvillage.entity.User;
import com.civoranexus.eduvillage.repository.CourseRepository;
import com.civoranexus.eduvillage.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       CourseRepository courseRepository,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public LoginResponse login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token, user.getEmail(), user.getRole());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void enrollCourse(Long userId, Long courseId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        user.getEnrolledCourses().add(course);
        userRepository.save(user);
    }
}
