package com.civoranexus.eduvillage.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.civoranexus.eduvillage.entity.Course;
import com.civoranexus.eduvillage.repository.CourseRepository;

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
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
