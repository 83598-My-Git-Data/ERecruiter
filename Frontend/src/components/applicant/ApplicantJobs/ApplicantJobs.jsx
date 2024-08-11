import { Link, NavLink, Outlet, useNavigate } from "react-router-dom";
import { logout } from "../../../services/helper"
import { useEffect, useState } from "react";
import { FetchUserDetailsInfo } from "../../../services/applicant";
function ApplicantJobs() {
  //useState for User Details api
  const [userDetails, setUserDetails] = useState({ firstName: "", lastName: "", gender: "", email: "", phoneNumber: "", dob: "" })
  const navigate=useNavigate();
  useEffect(() => {
    FetchUserDetailsInfo(userDetails, setUserDetails);
  }, [])
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
            <NavLink className="nav-link items" to={"/profile"}>
              {userDetails.firstName + " " + userDetails.lastName}
            </NavLink>
          </li>
          
          <li className="nav-item ">
            <button className="nav-link items" onClick={() => { logout(navigate) }}>Log out</button>
          </li>
        </ul>
      </div>
    </nav>
    {/* Navigation bar ends */}

    <div className="row">
      {/* side bar */}
      {/* For side bar */}
      <div className="col-2 h-100" style={{ backgroundColor: "#f5f5f5" }}>
        <h1 className="text-center">Welcome</h1>
        <h2 className="text-center">Applicant</h2>
        <hr />
        <div className="border border-2 text-center shadow-sm m-2"
          style={{ backgroundColor: "#BBADD9" }}>
          <NavLink
            to="/jobs/available-jobs"
            className=" link-underline link-underline-opacity-0">
            <h4 className="m-2" style={{ color: "black" }}>Available jobs</h4>
          </NavLink>
        </div>
        <br></br>
        <div className="border border-2 text-center shadow-sm m-2"
          style={{ backgroundColor: "#BBADD9" }}>
          <NavLink
            to="/jobs/saved-jobs"
            className=" link-underline link-underline-opacity-0">
            <h4 className="m-2" style={{ color: "black" }}>Saved jobs</h4>
          </NavLink>
        </div>
        <br></br>
        <div className="border border-2 text-center shadow-sm m-2"
          style={{ backgroundColor: "#BBADD9" }}>
          <NavLink
            to="/jobs/applied-jobs"
            className=" link-underline link-underline-opacity-0">
            <h4 className="m-2" style={{ color: "black" }}>Applied jobs</h4>
          </NavLink>
        </div>
        <br></br>
        <div className="border border-2 text-center shadow-sm m-2"
          style={{ backgroundColor: "#BBADD9" }}>
          <NavLink
            to="/jobs/shortlisted-jobs"
            className=" link-underline link-underline-opacity-0">
            <h4 className="m-2" style={{ color: "black" }}>Short-listed jobs</h4>
          </NavLink>
        </div>

      </div>
      {/* Outlet */}
      <div className="col-10" ><Outlet/></div>
    </div>

  </>);
}

export default ApplicantJobs;