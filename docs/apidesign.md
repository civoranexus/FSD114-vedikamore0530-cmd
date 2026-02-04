# API Design – EduVillage

## Authentication APIs
- POST /api/auth/register
- POST /api/auth/login

## User APIs
- GET /api/users/profile
- PUT /api/users/profile

## Course APIs
- POST /api/courses
- GET /api/courses
- GET /api/courses/{id}

## Enrollment APIs
- POST /api/enroll
- GET /api/enrollments

## Progress APIs
- GET /api/progress/{courseId}

## Authorization Rules
- Students cannot create or edit courses
- Teachers cannot access admin APIs
- Admins have full access

## Error Handling
- Proper HTTP status codes
- Clear error messages for invalid actions
