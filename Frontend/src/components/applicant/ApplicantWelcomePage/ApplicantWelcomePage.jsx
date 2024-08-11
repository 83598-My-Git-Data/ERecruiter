import "./ApplicantWelcomePage.css";
import { Link, useNavigate } from "react-router-dom";
import {logout} from "../../../services/helper"
function ApplicantWelcomePage() {
  /*use to navigate the function*/
  const navigate = useNavigate();
  return (<>
  <nav className="navbar navbar-expand-lg  background">
        <Link className="navbar-brand" to="/home">
          Get Hired
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item ">
            <button className="nav-link items" onClick={()=>{logout(navigate)}}>Log out</button>
            </li>
          </ul>
        </div>
      </nav>
    <div
      style={{ height: "100vh" }}
      className="d-flex align-items-center justify-content-center"
    >
      <div
        className="container"
        style={{ backgroundColor: "#f5f5f5", height: "100vh" }}
      >
        <div style={{ marginTop: "150px" }}>
          <h1 className="text-center">Welcome to GET HIRED</h1>
        </div>

        <div
          className="row d-flex justify-content-around  "
          style={{ marginTop: "120px" }}
        >
          <div
            className="col-5 d-flex justify-content-center"
            style={{ height: "100px" }}
          >
            <button
              className="btn btn-success "
              onClick={()=>{
                navigate("/jobs");
              }}
              style={{ width: "280px", fontSize: "25px" }}
            >
              Jobs
            </button>
          </div>
          <div
            className="col-5 d-flex justify-content-center"
            style={{ height: "100px" }}
          >
            <button onClick={()=>{
              navigate("/profile");
            }}
              className="btn btn-success   "
              style={{ width: "280px", fontSize: "25px" }}
            >
              Update Profile
            </button>
          </div>
        </div>
      </div>
    </div>
    </>
  );
}

export default ApplicantWelcomePage;
