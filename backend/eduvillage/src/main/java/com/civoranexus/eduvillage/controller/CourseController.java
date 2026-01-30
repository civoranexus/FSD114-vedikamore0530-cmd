package com.civoranexus.eduvillage.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import com.civoranexus.eduvillage.dto.CourseResponse;
import com.civoranexus.eduvillage.entity.Course;
import com.civoranexus.eduvillage.entity.User;
import com.civoranexus.eduvillage.repository.CourseRepository;
import com.civoranexus.eduvillage.repository.UserRepository;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseController(
            CourseRepository courseRepository,
            UserRepository userRepository
    ) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseResponse(
                        course.getId(),
                        course.getTitle(),
                        course.getDescription(),
                        course.getCategory()
                ))
                .toList();
    }

    @PostMapping("/{courseId}/enroll")
    public String enrollInCourse(
            @PathVariable Long courseId,
            Authentication authentication
    ) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 🔥 FIX: initialize enrolledCourses if null
        if (user.getEnrolledCourses() == null) {
            user.setEnrolledCourses(new HashSet<>());
        }

        user.getEnrolledCourses().add(course);
        userRepository.save(user);

        return "Enrolled successfully";
    }
}

