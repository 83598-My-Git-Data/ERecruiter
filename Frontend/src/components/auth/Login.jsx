import "./Login.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import { useAuth } from "../../services/authprovider";
import { jwtDecode } from "jwt-decode";
import { useNavigate } from "react-router-dom";

/*Toastify container*/
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function LogIn() {
  const [user, setUser] = useState({
    email: "",
    password: "",
  });

  /*To set the token in useAuth custom component*/
  const { setToken } = useAuth();

  /*use to navigate the function*/
  const navigate = useNavigate();

  const url = "http://localhost:8080/users/signin";
  const handleSubmit = (e) => {
    /*Prevent sending data in URL*/
    e.preventDefault();

    if (user.email === "" || user.password === "") {
      if (user.email === "") toast.error("enter email");
      if (user.password === "") toast.error("enter password");
    } else {
      axios
        .post(url, user)
        .then((result) => {
          let token = result.data["jwt"];
          let user = jwtDecode(token).authorities;
          setToken(token);
          if (user === "ROLE_HR") {
            navigate("/hr");
          } else if (user === "ROLE_ADMIN") {
            navigate("/admin");
          } else if (user === "ROLE_APPLICANT") {
            navigate("/applicant");
          }
        })
        .catch((error) => {
          if (error.response && error.response.status === 401) {
            // Bad credentials error
            toast.error("Invalid email or password. Please try again.");
          } else {
            // Handle other errors
            toast.error("An error occurred. Please try again later.");
          }
        });
    }
  };

  const OnTextChanged = (args) => {
    var copyOfUser = { ...user };
    copyOfUser[args.target.name] = args.target.value;
    setUser(copyOfUser);
  };

  return (
    <div className="container" style={{ marginTop: 100 }}>
      <div
        className="row justify-content-center my-5"
        style={{ backgroundColor: "#f5f5f5" }}
      >
        <div id="sign-in-bar" className="text-center">
          <h2>LogIn</h2>
        </div>
        <div className="col-6">
          <br></br>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>Email:</b>
              <input
                type="text"
                className="form-control"
                id="email"
                name="email"
                value={user.email}
                placeholder="safin@gmail.com"
                autoComplete="off"
                onChange={OnTextChanged}
                required
              />
            </div>
            <div className="form-group mt-2 mb-2">
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>Password:</b>
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                value={user.password}
                placeholder="Enter your password"
                autoComplete="off"
                onChange={OnTextChanged}
              />
              <div className="buttons">
                <input
                  type="submit"
                  className="btn btn-primary mt-2 mb-2"
                  onClick={handleSubmit}
                  style={{ backgroundColor: "#9B7ED9", width: 120, height: 50 }}
                  value={"LogIn"}
                ></input>
                <Link
                  className="link-offset-2 link-underline link-underline-opacity-0"
                  to={"/forgot-password"}
                >
                  Forgot Password
                </Link>
              </div>
            </div>
          </form>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
}

export default LogIn;
