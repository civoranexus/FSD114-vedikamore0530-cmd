package com.civoranexus.eduvillage.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.civoranexus.eduvillage.dto.CourseResponse;
import com.civoranexus.eduvillage.entity.Course;
import com.civoranexus.eduvillage.repository.CourseRepository;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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


}
