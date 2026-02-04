# Database Design – EduVillage

## Overview
The database is designed to support role-based access, course management, and student progress tracking.

## Core Tables
- users: Stores user credentials and roles
- courses: Stores course information created by teachers
- lessons: Stores lesson content linked to courses
- enrollments: Tracks student enrollment and progress

## Relationships
- One user (teacher) can create many courses
- One course can have multiple lessons
- Students can enroll in multiple courses
- Each enrollment stores progress percentage
