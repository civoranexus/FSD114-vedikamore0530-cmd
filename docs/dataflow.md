# Data Flow – EduVillage Platform

## Authentication Flow
1. User submits login credentials
2. Backend validates credentials
3. Authentication token is issued
4. Token is used for authorized API access

## Course Access Flow
1. Student requests course list
2. Backend fetches courses from database
3. Student enrolls in a course
4. Enrollment and progress data is stored

## Progress Tracking Flow
1. Student completes lesson or quiz
2. Backend updates progress percentage
3. Progress is reflected on student dashboard
