import { useEffect, useState } from "react";

function Dashboard() {
  const [courses, setCourses] = useState([]);
  const [message, setMessage] = useState("");

  const token = localStorage.getItem("token");

  useEffect(() => {
    fetch("http://localhost:8090/api/courses", {
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((res) => res.json())
      .then((data) => setCourses(data))
      .catch((err) => console.log(err));
  }, []);

  const enrollCourse = (courseId) => {
    fetch("http://localhost:8090/api/courses/" + courseId + "/enroll", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("Already enrolled or bad request");
        }
        return res.text();
      })
      .then((data) => setMessage(data))
      .catch(() => setMessage("You are already enrolled in this course"));  
  };

  return (
    <div>
      <h2>Student Dashboard</h2>

      {message && <p>{message}</p>}

      <ul>
        {courses.map((course) => (
          <li key={course.id}>
            <strong>{course.title}</strong> – {course.description}
            <br />
            <button onClick={() => enrollCourse(course.id)}>
              Enroll
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Dashboard;
