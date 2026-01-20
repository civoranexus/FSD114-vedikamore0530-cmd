package com.civoranexus.eduvillage.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.civoranexus.eduvillage.dto.CourseResponse;
import com.civoranexus.eduvillage.entity.Course;
import com.civoranexus.eduvillage.repository.CourseRepository;
import com.civoranexus.eduvillage.repository.UserRepository;

import org.springframework.security.core.Authentication;
import com.civoranexus.eduvillage.entity.User;



@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

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

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getCategory()
        );
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

        user.getEnrolledCourses().add(course);
        userRepository.save(user);

        return "Enrolled successfully";
    }

}
