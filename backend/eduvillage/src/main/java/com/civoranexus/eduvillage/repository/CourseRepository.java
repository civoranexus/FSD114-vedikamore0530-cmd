package com.civoranexus.eduvillage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.civoranexus.eduvillage.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
