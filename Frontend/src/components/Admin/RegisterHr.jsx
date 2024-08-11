import { useState } from "react";
import { registerHR } from "../../services/admin";
import { ToastContainer, toast } from "react-toastify";
function RegisterHr() {
  /*Details of HR*/
  const [hrDetail, setHrDetail] = useState({
    firstName: "",
    lastName: "",
    gender: "",
    email: "",
    phoneNumber: "",
    password: "",
    dob: "",
    officeLocation: "",
    department: "",
    qualification: "",
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

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
    setHrDetail({
      ...hrDetail,
      gender: genderValue, // Update the gender field in the state
    });
  };

  const OnTextChanged = (args) => {
    var copyOfData = { ...hrDetail };
    copyOfData[args.target.name] = args.target.value;
    setHrDetail(copyOfData);
  };

  /*Send data to backend*/
 async function sendData() {
    // Check if any field in hrDetail is empty
    const isEmptyField = Object.values(hrDetail).some((value) => value === "");

    if (isEmptyField) {
      toast.error("Please fill in all fields");
      return;
    }

    await registerHR(hrDetail)
      .then((response) => {
        toast.success("HR registered successfully!");

        // Reset hrDetail state after successful registration
        resetFileds();
      })
      .catch((err) => {
        toast.error("Error ocurred"+err["message"]);
        console.log("error called:"+err);
      });
  }

  const resetFileds=()=>{
    setHrDetail({
      firstName: "",
      lastName: "",
      gender: "",
      email: "",
      phoneNumber: "",
      password: "",
      dob: "",
      officeLocation: "",
      department: "",
      qualification: "",
    })
  }


  return (
    <>
      <h1>Register HR</h1>
      <hr />
      <div className="container">
        <div className="row">
          <div className="col-6">
            <div className="mb-3">
              <label htmlFor="firstName" className="form-label">
                FirstName
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Jhon"
                onChange={OnTextChanged}
                value={hrDetail.firstName}
                name="firstName"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                name="email"
                type="email"
                className="form-control"
                placeholder="xyz@gmail.com"
                value={hrDetail.email}
                onChange={OnTextChanged}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="mobile-number" className="form-label">
                Mobile Number
              </label>
              <input
                name="phoneNumber"
                type="number"
                className="form-control"
                placeholder=""
                value={hrDetail.phoneNumber}
                onChange={OnTextChanged}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="dob" className="form-label">
                Date of birth
              </label>
              <input
                name="dob"
                type="date"
                className="form-control"
                onChange={OnTextChanged}
                value={hrDetail.dob}
              />
            </div>
            <div className="mb-3 mt-3">
              <label htmlFor="qualification" className="form-label">
                Qualification
              </label>
              <input
                type="text"
                name="qualification"
                className="form-control"
                placeholder=""
                onChange={OnTextChanged}
                value={hrDetail.qualification}
              />
            </div>
          </div>

          <div className="col-6">
            <label htmlFor="lastName" className="form-label">
              LastName
            </label>
            <input
              name="lastName"
              type="text"
              className="form-control"
              placeholder="Doe"
              onChange={OnTextChanged}
              value={hrDetail.lastName}
            />
            <label htmlFor="gender" className="form-label mt-4">
              Gender
            </label>
            <br></br>
            <div className="form-check form-check-inline mt-1">
              <input
                name="gender"
                className="form-check-input"
                type="radio"
                value="MALE"
                checked={hrDetail.gender === "MALE"}
                onChange={handleGenderChange}
              />
              <label className="form-check-label" htmlFor="inlineCheckbox1">
                Male
              </label>
            </div>
            <div className="form-check form-check-inline">
              <input
                name="gender"
                className="form-check-input"
                type="radio"
                value="FEMALE"
                checked={hrDetail.gender === "FEMALE"}
                onChange={handleGenderChange}
              />
              <label className="form-check-label" htmlFor="inlineCheckbox2">
                Female
              </label>
            </div>
            <div className="form-check form-check-inline">
              <input
                name="gender"
                className="form-check-input"
                type="radio"
                value="OTHER"
                checked={hrDetail.gender === "OTHER"}
                onChange={handleGenderChange}
              />
              <label className="form-check-label" htmlFor="inlineCheckbox3">
                Other
              </label>
            </div>
            <div className="mb-3 mt-3">
              <label htmlFor="password" className="form-label">
                Password
              </label>
              <input
                name="password"
                type="password"
                className="form-control"
                placeholder=""
                onChange={OnTextChanged}
                value={hrDetail.password}
              />
            </div>
            <div className="mb-3 mt-3">
              <label htmlFor="office-location" className="form-label">
                Office Location
              </label>
              <input
                name="officeLocation"
                type="text"
                className="form-control"
                placeholder="Pune,Banglore,etc..."
                onChange={OnTextChanged}
                value={hrDetail.officeLocation}
              />
            </div>
            <div className="mb-3 mt-3">
              <label htmlFor="department" className="form-label">
                Department
              </label>
              <input
                type="text"
                name="department"
                className="form-control"
                placeholder="IT,Finance,etc"
                onChange={OnTextChanged}
                value={hrDetail.department}
              />
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-2">
            <button
              type="button"
              className="btn shadow-sm"
              style={{ backgroundColor: "#49A646" }}
              onClick={() => {
                sendData();
              }}
            >
              Register
            </button>
          </div>
          <div className="col-2">
            <button
              type="button"
              className="btn shadow-sm"
              style={{ backgroundColor: "#F28E13" }}
              onClick={()=>{resetFileds()}}
            >
              Reset
            </button>
          </div>
        </div>
      </div>
      <ToastContainer />
    </>
  );
}

export default RegisterHr;
