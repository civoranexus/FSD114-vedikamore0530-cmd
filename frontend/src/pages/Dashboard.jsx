import { useEffect, useState } from "react";

function Dashboard() {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:8090/api/courses", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => setCourses(data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      <h2>Student Dashboard</h2>

      {courses.length === 0 ? (
        <p>No courses available</p>
      ) : (
        <ul>
          {courses.map((course) => (
            <li key={course.id}>
              <strong>{course.title}</strong> – {course.description}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default Dashboard;
