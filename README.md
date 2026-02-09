# EduVillage Learning Platform

Full Stack Course Management Application.

## Features
- User Authentication (JWT)
- View Courses
- Enroll / Unenroll Courses
- Dashboard
- Course Details Page

## Project Structure

backend/   → Spring Boot API  
frontend/  → React App  
database/  → SQL schema  
docs/      → Project documentation  

## Tech Stack

Frontend
- React
- Vite
- CSS

Backend
- Spring Boot
- Spring Security
- JWT
- JPA / Hibernate
- MySQL

## How to Run

### 1. Database
Import database/schema.sql into MySQL

### 2. Backend
cd backend  
mvn spring-boot:run  

Runs on:
http://localhost:8090

### 3. Frontend
cd frontend  
npm install  
npm run dev  

Runs on:
http://localhost:5173

## Demo Flow
1. Register user  
2. Login  
3. View courses  
4. Enroll / Unenroll  
5. Open course details  

---

## Author
Vedika More

---

## Final Submission Notes

### Completed Modules

* Authentication with JWT
* Course listing API
* Enroll / Unenroll functionality
* User-specific dashboard
* Course details navigation
* UI theme implementation

### API Endpoints Used

* POST /api/users/register
* POST /api/users/login
* GET /api/courses
* POST /api/courses/{id}/enroll
* DELETE /api/courses/{id}/unenroll
* GET /api/users/me/courses

### Status

Project completed and ready for submission.


