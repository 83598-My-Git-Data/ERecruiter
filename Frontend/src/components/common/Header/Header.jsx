import "./Header.css";
import { NavLink } from "react-router-dom";
function Header() {
  return (
    <div>
      <nav className="navbar fixed-top navbar-expand-lg  background">
        <a className="navbar-brand" href="/Home">
          ERecruiter
        </a>
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
          <ul className="navbar-nav ms-auto ">
            <li className="nav-item">
              <NavLink className="nav-link items" to={"/home"}>
                Home
              </NavLink>
            </li>
            <li className="nav-item ">
              <NavLink className="nav-link items" to={"/contact-us"}>
                Contact us
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link items" to={"/about-us"}>
                AboutUs
              </NavLink>
            </li>
            <li className="nav-item ">
              <NavLink className="nav-link items" to={"/signin"}>
                SignIn
              </NavLink>
            </li>
            <li className="nav-item ">
              <NavLink className="nav-link items" to={"/signup"}>
                SignUp
              </NavLink>
            </li>
          </ul>
        </div>
      </nav>
    </div>
  );
}

export default Header;
