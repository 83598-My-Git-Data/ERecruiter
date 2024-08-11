import { useState } from "react";
import axios from "axios";
import "../auth/Register.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";

function Register() {
  let message = "message";
  let passwordError = "password";
  let dob = "date";
  const url = "http://localhost:8080/users/signup";


  const navigate = useNavigate();
  const [sendData, setSendUpData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    phoneNumber: "",
    dob: "",
    gender: "",
  });

  const OnTextChanged = (args) => {
    var copyOfData = { ...sendData };
    copyOfData[args.target.name] = args.target.value;
    setSendUpData(copyOfData);
  };

  async function SignUp() {
    await axios

      .post(url, sendData)

      .then((resp) => {
        console.log("ReponseData :" + resp.data);
        navigate("/signin")
        toast.success("Signed up succesfully.");
        ResetInput()

      })

      .catch((error) => {
        if (error.response.data["message"]) {
          message = error.response.data["message"];
        }
        if (error.response.data["password"]) {
          passwordError = error.response.data["password"];
        }
        if (error.response.data["dob"]) {
          dob = error.response.data["dob"];
        }

        console.log("Error Message :" + error.response.data["dob"]);
        if (message.includes("Duplicate")) {
          toast.error(
            "User with this email " + sendData.email + " already exists"
          );
        } else if (passwordError.includes("strong")) {
          toast.error(
            "Enter a strong password, password must contain atleast one alphabet character, one numeric charater,and one special charater amoun '@$!%*#?&'"
          );
        } else if (dob.includes("DOB")) {
          toast.error("Date of birth should be from past");
        }
      });
  }

  function ResetInput() {
    setSendUpData({
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      phoneNumber: "",
      dob: "",
      gender: "",
    })
  }

  function validateValues() {
    const { firstName, lastName, email, password, phoneNumber, dob, gender } =
      sendData;
    let isValid = true;
    const currentDate = new Date();
    const selectedDate = new Date(dob);
    if (!firstName.trim()) {
      toast.error("Please enter your first name.");

      isValid = false;
    }

    if (!lastName.trim()) {
      toast.error("Please enter your last name.");
      isValid = false;
    }

    if (!email.trim()) {
      toast.error("Please enter your email.");
      isValid = false;
    } else if (!isValidEmail(email)) {
      toast.error("Please enter a valid email address.");
      isValid = false;
    }

    if (!password.trim()) {
      toast.error("Please enter your password.");
      isValid = false;
    } else if (!isValidPassword(password)) {
      toast.error("Please enter a valid password.");
      isValid = false;
    }

    if (!phoneNumber.trim()) {
      toast.error("Please enter your phone number.");
      isValid = false;
    } else if (!isValidPhoneNumber(phoneNumber)) {
      toast.error("Please enter a valid phone number.");
      isValid = false;
    }

    if (!dob.trim()) {
      toast.error("Please enter your date of birth.");
      isValid = false;
    } else if (selectedDate >= currentDate) {
      toast.error("Date of birth should be in the past.");
      isValid = false;
    }

    if (!gender.trim()) {
      toast.error("Please select your gender.");
      isValid = false;
    }
    if (isValid) {
      SignUp();
    }

    return isValid;
  }

  function isValidEmail(email) {
    return /\S+@\S+\.\S+/.test(email);
  }

  function isValidPhoneNumber(phoneNumber) {
    // Example validation logic for phone number
    return /^\d{10}$/.test(phoneNumber);
  }

  const isValidPassword = (password) => {
    // Password validation using regex
    return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/.test(password);
  };

  const handleGenderChange = (event) => {
    const genderValue = event.target.value; // Get the selected gender value
    setSendUpData({
      ...sendData,
      gender: genderValue, // Update the gender field in the state
    });
  };

  return (
    <div class="container " style={{ marginBottom: "120px" ,marginTop: "120px" }}>
      <div
        className=" row justify-content-center   my-5 "
        style={{
          backgroundColor: "#F5F5F5",
          marginTop: "100",
          marginBottom: "120px",
        }}
      >
        <div id="signUpBar" className=" text-center  ">
          <h2>SignUp</h2>
        </div>

        <div className="col-6">
          <br />
          <form>
            <div className="form-group">
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>First Name</b>
              <input
                type="text"
                className="form-control"
                id="firstName"
                name="firstName"
                value={sendData.firstName}
                placeholder="Enter first name"
                autoComplete="off"
                onChange={OnTextChanged}
              />
            </div>
            <br />
            <div className="form-group">
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>Last Name</b>
              <input
                type="text"
                className="form-control"
                id="lastName"
                name="lastName"
                value={sendData.lastName}
                placeholder="Enter Last name"
                autoComplete="off"
                onChange={OnTextChanged}
              />
            </div>
            <br />
            <div className="form-group">
              <div style={{ marginBottom: 18 }}>
                <b style={{ color: "#9B7ED9", fontSize: 22 }}>Gender</b>
              </div>

              <input
                className="form-check-input"
                type="radio"
                name="MALE"
                id="MALE"
                checked={sendData.gender === "MALE"}
                onChange={handleGenderChange}
                value="MALE"
              />
              <b style={{ color: "#9B7ED9", fontSize: 20 }}> Male</b>
              <label
                className="form-check-label"
                style={{ marginRight: "20px" }}
              ></label>
              <input
                className="form-check-input"
                type="radio"
                name="FEMALE"
                checked={sendData.gender === "FEMALE"}
                onChange={handleGenderChange}
                value="FEMALE"
              />

              <b style={{ color: "#9B7ED9", fontSize: 20 }}> Female</b>
              <label
                className="form-check-label"
                style={{ marginRight: "20px" }}
              ></label>
              <input
                className="form-check-input"
                type="radio"
                name="OTHER"
                id="OTHER"
                checked={sendData.gender === "OTHER"}
                onChange={handleGenderChange}
                value="OTHER"
              />
              <b style={{ color: "#9B7ED9", fontSize: 20 }}> Other</b>
            </div>
            <br />
            <div className="form-group">
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>Email ID</b>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                value={sendData.email}
                placeholder="Enter email ID"
                autoComplete="off"
                onChange={OnTextChanged}
              />
            </div>
            <br />
            <div className="form-group">
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>Password</b>
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                value={sendData.password}
                placeholder="Enter password"
                autoComplete="off"
                onChange={OnTextChanged}
              />
            </div>
            <br />
            <div className="form-group">
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>Date of birth</b>
              <input
                type="date"
                className="form-control"
                id="dob"
                name="dob"
                value={sendData.dob}
                autoComplete="off"
                onChange={OnTextChanged}
              />
            </div>
            <br />
            <div className="form-group">
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>Phone number</b>
              <input
                type="text"
                className="form-control"
                id="phoneNumber"
                name="phoneNumber"
                value={sendData.phoneNumber}
                placeholder="Enter mobile number"
                autoComplete="off"
                onChange={OnTextChanged}
              />
            </div>
            <br />
            <button
              type="button"

              className="btn btn-primary"
              onClick={() => {
                validateValues();
              }}
              style={{ backgroundColor: "#9B7ED9", width: 120, height: 50 }}
            >
              Sign Up
            </button>
            <b
              style={{
                color: "#9B7ED9",
                fontSize: 22,
                marginLeft: "80px",
                marginRight: "10px",
              }}
            >
              Already registered ?
            </b>
            <a href="/signin" style={{ fontSize: "22px" }}>
              Sign in
            </a>
            <b style={{ color: "#9B7ED9", fontSize: 22, marginLeft: "10px" }}>
              here
            </b>
          </form>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
}

export default Register;
