
import { useLocation, useNavigate } from "react-router-dom";

function CourseDetails() {
  const location = useLocation();
  const navigate = useNavigate();

  const course = location.state?.course;

  if (!course) {
    return <p style={{ padding: "20px" }}>Course not found</p>;
  }

  return (
    <div className="container">
      <button className="btn-primary" onClick={() => navigate(-1)}>
        ← Back
      </button>

      <div className="card" style={{ marginTop: "16px" }}>
        <h2>{course.title}</h2>
        <p><strong>Category:</strong> {course.category}</p>
        <p>{course.description}</p>
      </div>
    </div>
  );
}

export default CourseDetails;
