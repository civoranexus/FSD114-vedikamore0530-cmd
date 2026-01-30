import { useEffect, useState } from "react";

function Dashboard() {
  const [courses, setCourses] = useState([]);
  const [myCourses, setMyCourses] = useState([]);
  const [message, setMessage] = useState("");

  const token = localStorage.getItem("token");

  const logout = () => {
    localStorage.removeItem("token");
    window.location.href = "/";
  };

  // 🔹 Fetch all courses
  const fetchCourses = () => {
  fetch("http://localhost:8090/api/courses", {
    headers: {
      Authorization: "Bearer " + token,
    },
  })
    .then((res) => {
      if (!res.ok) {
        throw new Error("Failed to fetch courses");
      }
      return res.json();
    })
    .then((data) => setCourses(data))
    .catch((err) => {
      console.log(err);
      setCourses([]);
    });
};


  // 🔹 Fetch enrolled courses
  const fetchMyCourses = () => {
    fetch("http://localhost:8090/api/users/me/courses", {
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((res) => res.json())
      .then((data) => setMyCourses(data))
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    fetchCourses();
    fetchMyCourses();
  }, []);

  // 🔹 Enroll course
  const enrollCourse = (courseId) => {
    fetch(`http://localhost:8090/api/courses/${courseId}/enroll`, {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error();
        }
        return res.text();
      })
      .then((msg) => {
        setMessage(msg);
        fetchMyCourses(); // refresh enrolled courses
      })
      .catch(() =>
        setMessage("You are already enrolled in this course")
      );
  };

  // 🔹 Filter available courses (remove enrolled ones)
  const enrolledIds = myCourses.map((c) => c.id);
  const availableCourses = courses.filter(
    (course) => !enrolledIds.includes(course.id)
  );

  return (
    <>
      {/* Header */}
      <div className="header">
        <h2>EduVillage</h2>
        <button className="btn-primary" onClick={logout}>
          Logout
        </button>
      </div>

      {/* Main Content */}
      <div className="container">

        {message && <p className="empty-text">{message}</p>}

        {/* Available Courses */}
        <div className="card">
          <h3>Available Courses</h3>

          {availableCourses.length === 0 ? (
            <p className="empty-text">No available courses.</p>
          ) : (
            availableCourses.map((course) => (
              <div key={course.id} style={{ marginBottom: "14px" }}>
                <strong>{course.title}</strong> – {course.description}
                <br />
                <button
                  className="btn-primary"
                  style={{ marginTop: "6px" }}
                  onClick={() => enrollCourse(course.id)}
                >
                  Enroll
                </button>
              </div>
            ))
          )}
        </div>

        {/* My Enrolled Courses */}
        <div className="card">
          <h3>My Enrolled Courses</h3>

          {myCourses.length === 0 ? (
            <p className="empty-text">
              You haven’t enrolled in any courses yet.
            </p>
          ) : (
            myCourses.map((course) => (
              <div key={course.id}>
                <strong>{course.title}</strong>
              </div>
            ))
          )}
        </div>
      </div>
    </>
  );
}

export default Dashboard;
