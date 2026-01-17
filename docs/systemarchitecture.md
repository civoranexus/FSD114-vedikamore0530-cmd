# System Architecture – EduVillage

## Architecture Overview
EduVillage follows a standard three-tier architecture to ensure scalability, maintainability, and security.

## Layers

### 1. Frontend Layer
- Built using React
- Provides user interfaces for Students, Teachers, and Admins
- Communicates with the backend via REST APIs

### 2. Backend Layer
- Developed using Node.js and Express
- Handles business logic, authentication, and authorization
- Manages role-based access control for different users

### 3. Database Layer
- Relational database (MySQL / PostgreSQL)
- Stores user data, course content, enrollments, and progress

## Security Considerations
- Role-based access control (RBAC)
- Secure authentication using tokens
- Validation of user inputs

## Scalability
- Modular backend design
- Clear separation of concerns
- Extendable database schema
