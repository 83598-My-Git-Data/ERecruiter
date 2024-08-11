import { Link, NavLink, Outlet,useNavigate } from "react-router-dom";
import "./Admin.css";

import { logout } from "../../services/helper";


function AdminDashboard() {
  const navigate =useNavigate();
  return (
    <>
      <nav className="navbar navbar-expand-lg  background">
        <Link className="navbar-brand" href="/">
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
      <div className="row" style={{ height: "100vh" }}>
        {/* For side bar */}
        <div className="col-2 h-100" style={{ backgroundColor: "#f5f5f5" }}>
          <h1 className="text-center">Welcome</h1>
          <h2 className="text-center">Admin</h2>
          <hr />
          <div className="border border-2 text-center shadow-sm m-2"
          style={{backgroundColor:"#BBADD9"}}>
            <NavLink
              to="/admin/register-hr"
              className=" link-underline link-underline-opacity-0">
              <h4 className="m-2" style={{color:"black"}}>Register HR</h4>
            </NavLink>
          </div>
          <br></br>
          <div className="border border-2 text-center shadow-sm m-2"
          style={{backgroundColor:"#BBADD9"}}>
            <NavLink
              to="/admin/hr-list"
              className=" link-underline link-underline-opacity-0">
              <h4 className="m-2" style={{color:"black"}}>HR List</h4>
            </NavLink>
          </div>
          <br></br>
          <div className="border border-2 text-center shadow-sm m-2"
          style={{backgroundColor:"#BBADD9"}}>
            <NavLink
              to="/admin/job-list"
              className=" link-underline link-underline-opacity-0">
              <h4 className="m-2" style={{color:"black"}}>Job List</h4>
            </NavLink>
          </div>
          <br></br>
          <div className="border border-2 text-center shadow-sm m-2"
          style={{backgroundColor:"#BBADD9"}}>
            <NavLink
              to="/admin/report"
              className=" link-underline link-underline-opacity-0">
              <h4 className="m-2" style={{color:"black"}}>Report</h4>
            </NavLink>
          </div>
          
        </div>
        {/* For dashboard cards */}
        <div className="col-10"> <Outlet /></div>
      </div>
      
    </>
  );
}

export default AdminDashboard;
