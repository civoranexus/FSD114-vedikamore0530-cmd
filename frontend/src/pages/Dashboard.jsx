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
    fetch("http://localhost:8090/api/courses")
      .then((res) => res.json())
      .then((data) => setCourses(data))
      .catch(() => setCourses([]));
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
      .catch(() => setMyCourses([]));
  };

  useEffect(() => {
    fetchCourses();
    fetchMyCourses();
  }, []);

  // ✅ AUTO-HIDE MESSAGE AFTER 2 SECONDS (THIS WAS MISSING)
  useEffect(() => {
    if (!message) return;

    const timer = setTimeout(() => {
      setMessage("");
    }, 2000);

    return () => clearTimeout(timer);
  }, [message]);

  // 🔹 Enroll course
  const enrollCourse = (course) => {
    fetch(`http://localhost:8090/api/courses/${course.id}/enroll`, {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((res) => {
        if (!res.ok) throw new Error();
        return res.text();
      })
      .then(() => {
        setMessage("Enrolled successfully");
        setMyCourses((prev) => [...prev, course]);
      })
      .catch(() =>
        setMessage("You are already enrolled in this course")
      );
  };

  // 🔹 Unenroll course
  const unenrollCourse = (courseId) => {
    fetch(`http://localhost:8090/api/courses/${courseId}/unenroll`, {
      method: "DELETE",
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((res) => {
        if (!res.ok) throw new Error();
        return res.text();
      })
      .then(() => {
        setMessage("Unenrolled successfully");
        setMyCourses((prev) =>
          prev.filter((course) => course.id !== courseId)
        );
      })
      .catch(() =>
        setMessage("Failed to unenroll from course")
      );
  };

  return (
    <>
      {/* Header */}
      <div className="header">
        <h2>EduVillage</h2>
        <button className="btn-primary" onClick={logout}>
          Logout
        </button>
      </div>

      <div className="container">
        {message && <p className="empty-text">{message}</p>}

        {/* Available Courses */}
        <div className="card">
          <h3>Available Courses ({courses.length})</h3>

          {courses.map((course) => {
            const isEnrolled = myCourses.some(
              (c) => c.id === course.id
            );

            return (
              <div key={course.id} style={{ marginBottom: "14px" }}>
                <strong>{course.title}</strong> – {course.description}
                <br />
                <button
                  className="btn-primary"
                  style={{
                    marginTop: "6px",
                    backgroundColor: isEnrolled ? "#9ca3af" : "",
                    cursor: isEnrolled ? "not-allowed" : "pointer",
                  }}
                  disabled={isEnrolled}
                  onClick={() => enrollCourse(course)}
                >
                  {isEnrolled ? "Enrolled" : "Enroll"}
                </button>
              </div>
            );
          })}
        </div>

        {/* My Enrolled Courses */}
        <div className="card">
          <h3>My Enrolled Courses ({myCourses.length})</h3>

          {myCourses.length === 0 ? (
            <p className="empty-text">
              You haven’t enrolled in any courses yet.
            </p>
          ) : (
            myCourses.map((course) => (
              <div key={course.id} style={{ marginBottom: "10px" }}>
                <strong>{course.title}</strong>
                <br />
                <button
                  className="btn-primary"
                  style={{
                    marginTop: "4px",
                    backgroundColor: "#ef4444",
                  }}
                  onClick={() => unenrollCourse(course.id)}
                >
                  Unenroll
                </button>
              </div>
            ))
          )}
        </div>
      </div>
    </>
  );
}

export default Dashboard;



