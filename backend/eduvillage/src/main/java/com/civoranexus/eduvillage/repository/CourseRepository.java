package com.civoranexus.eduvillage.repository;

import com.civoranexus.eduvillage.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
