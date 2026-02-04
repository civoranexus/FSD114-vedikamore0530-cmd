import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import App from "./App";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import CourseDetails from "./pages/CourseDetails";


// ✅ IMPORTANT: IMPORT CSS HERE
import "./App.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/course" element={<CourseDetails />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
