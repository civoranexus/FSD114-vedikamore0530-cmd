import { useLocation, useNavigate } from "react-router-dom";

function CourseDetails() {
  const location = useLocation();
  const navigate = useNavigate();

  const course = location.state?.course;

  if (!course) {
    return (
      <div className="container">
        <p className="empty-text">Course not found.</p>
        <button className="btn-primary" onClick={() => navigate("/dashboard")}>
          Go back
        </button>
      </div>
    );
  }

  // 🔹 Derived / demo data (frontend-only)
  const duration = "6 Weeks";
  const level = "Beginner";
  const instructor = "EduVillage Instructor";
  const outcomes = [
    "Understand core concepts",
    "Build hands-on projects",
    "Gain practical experience",
    "Prepare for real-world applications",
  ];

  return (
    <>
      {/* Header */}
      <div className="header">
        <h2>EduVillage</h2>
        <button className="btn-primary" onClick={() => navigate("/dashboard")}>
          Back to Dashboard
        </button>
      </div>

      <div className="container">
        <div className="card">
          <h2>{course.title}</h2>

          <p style={{ marginTop: "8px", color: "#6B7280" }}>
            <strong>Category:</strong> {course.category || "General"}
          </p>

          <p style={{ marginTop: "4px", color: "#6B7280" }}>
            <strong>Level:</strong> {level}
          </p>

          <p style={{ marginTop: "4px", color: "#6B7280" }}>
            <strong>Duration:</strong> {duration}
          </p>

          <p style={{ marginTop: "4px", color: "#6B7280" }}>
            <strong>Instructor:</strong> {instructor}
          </p>

          <hr style={{ margin: "16px 0" }} />

          <h3>About this course</h3>
          <p style={{ marginTop: "8px" }}>
            {course.description}
          </p>

          <h3 style={{ marginTop: "16px" }}>What you’ll learn</h3>
          <ul style={{ marginTop: "8px", paddingLeft: "20px" }}>
            {outcomes.map((item, index) => (
              <li key={index} style={{ marginBottom: "6px" }}>
                {item}
              </li>
            ))}
          </ul>
        </div>
      </div>
    </>
  );
}

export default CourseDetails;



