package com.civoranexus.eduvillage.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.civoranexus.eduvillage.entity.Course;
import com.civoranexus.eduvillage.entity.User;
import com.civoranexus.eduvillage.repository.CourseRepository;
import com.civoranexus.eduvillage.repository.UserRepository;

@Service
public class UserService {

    

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserService(UserRepository userRepository,
                       CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(String email, String password) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    return "Login successful";
}



    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    
    public void enrollCourse(Long userId, Long courseId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));

    if (user.getEnrolledCourses() == null) {
        user.setEnrolledCourses(new HashSet<>());
    }

    user.getEnrolledCourses().add(course);
    userRepository.save(user);
}

}
