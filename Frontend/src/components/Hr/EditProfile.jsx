import { useEffect, useState } from "react";
import { getHrDetails ,updateProfile,updateValue} from "../../services/hr";

export const EditHrProfile = () => {
  /*Holds hr details*/
  const [sendhrDetail, setSendHrDetail] = useState({
    id: 0,
    qualification: "",
    user: {
      firstName: "",
      lastName: "",
      gender: "",
      email: "",
      phoneNumber: "",
      dob: "",
      password:""
    },
  });

  // Add a new state variable to handle the input values
  const [inputValues, setInputValues] = useState({
    id: 0,
    qualification: "",
    status: true,
    imageURL: "",
    firstName: "",
    lastName: "",
    gender: "",
    email: "",
    phoneNumber: "",
    dob: "",
    password: "",
  });

  // Fetch data from getHrDetails when the component mounts
  const fetchData = async () => {
    try {
      const response = await getHrDetails();
        inputValues.id=response.id
        inputValues.firstName= response.user.firstName;
        inputValues.lastName= response.user.lastName;
        inputValues.email= response.user.email;
        inputValues.phoneNumber= response.user.phoneNumber;
        inputValues.gender= response.user.gender;
        inputValues.dob= response.user.dob;
        inputValues.qualification= response.qualification;
    } catch (error) {
      console.error("Error fetching HR details:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  // function to handle input changes
  const OnTextChanged = (args) => {
    var copyOfUser = { ...inputValues };
    copyOfUser[args.target.name] = args.target.value;
    setInputValues(copyOfUser);
  };

  const handleGenderChange = (event) => {
    const genderValue = event.target.value; // Get the selected gender value
    setInputValues({
      ...inputValues,
      gender: genderValue, // Update the gender field in the state
    });
  };


  const mapDetailsAndSendToBackend = async () => {
    try {
      // Map the details from inputValues to sendHrDetail
      setSendHrDetail({
        id: inputValues.id,
        qualification: inputValues.qualification,
        user: {
          firstName: inputValues.firstName,
          lastName: inputValues.lastName,
          gender: inputValues.gender,
          email: inputValues.email,
          phoneNumber: inputValues.phoneNumber,
          dob: inputValues.dob,
          password: inputValues.password,
        },
      });
  
      // Assuming you have a function to send data to the backend, for example, sendHrDetailsToBackend
      await updateProfile(sendhrDetail);
      console.log("HR details sent to the backend successfully!");
    } catch (error) {
      console.error("Error sending HR details to the backend:", error);
    }
  };
  


  return (
    <div className={"m-3"}>
      <h1>Edit profile</h1>
      <div className="row">
        <div className="col-6">
          <div className="mb-3">
            <label htmlFor="firstName" className="form-label">
              FirstName
            </label>
            <input
              name="firstName"
              type="text"
              className="form-control"
              placeholder=""
              onChange={OnTextChanged}
              value={inputValues.firstName}
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
              onChange={OnTextChanged}
              value={inputValues.email}
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
              onChange={OnTextChanged}
              value={inputValues.phoneNumber}
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
              value={inputValues.dob}
              onChange={OnTextChanged}
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
              value={inputValues.qualification}
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
            value={inputValues.lastName}
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
              onChange={handleGenderChange}
              checked={inputValues.gender === "MALE"}
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
              onChange={handleGenderChange}
              checked={inputValues.gender === "FEMALE"}
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
              onChange={handleGenderChange}
              checked={inputValues.gender === "OTHER"}
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
              value={inputValues.password}
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
                mapDetailsAndSendToBackend();
            }}
          >
            Update
          </button>
        </div>
        <div className="col-2">
          <button
            type="button"
            className="btn shadow-sm"
            style={{ backgroundColor: "#F28E13" }}
            onClick={() => {}}
          >
            Reset
          </button>
        </div>
      </div>
    </div>
  );
};
