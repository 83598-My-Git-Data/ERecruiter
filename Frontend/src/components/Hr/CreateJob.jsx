import { useState } from "react";
import { createJobAndSave } from "../../services/hr";
import { ToastContainer, toast } from "react-toastify";
export const CreateJob = () => {
  const [job, setJob] = useState({
    jobTitle: "", //
    experienceRequired: 0,
    workSchedule: "", //
    salary: 0, //
    applicationDeadline: "", //
    location: "", //
    qualification: "", //
    depId: 0, //
    vacancies: 0, //
    status: true,
  });

  // function to handle input changes
  const OnTextChanged = (args) => {
    var copyOfUser = { ...job };
    copyOfUser[args.target.name] = args.target.value;
    setJob(copyOfUser);
  };

  //create job
  const createJob = () => {
    /*Check if all fields are filled*/
    const isEmptyField = Object.values(job).some((value) => value === "");
    if (isEmptyField) {
      toast.error("Please fill in all fields");
      return;
    }
    createJobAndSave(job)
      .then((response) => {
        toast.success("Job created successfully!");
        resetAllFields();
      })
      .catch((err) => {
        toast.error("something bad happened");
      });
  };

  const resetAllFields = () => {
    console.log("Before:" + job);
    setJob({
      jobTitle: "",
      experienceRequired: 0,
      workSchedule: "",
      salary: 0, //
      applicationDeadline: "",
      location: "",
      qualification: "",
      depId: 0,
      vacancies: 0,
      status: true,
      description: "",
    });
    console.log("After:" + job);
  };
  return (
    <div className={"m-3"}>
      <h1>Create Job:</h1>
      <div className="row">
        <div className="col-6">
          <div className="mb-3">
            <label htmlFor="firstName" className="form-label">
              Job Title
            </label>
            <input
              name="jobTitle"
              type="text"
              className="form-control"
              placeholder=""
              onChange={OnTextChanged}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Location
            </label>
            <input
              name="location"
              type="text"
              className="form-control"
              onChange={OnTextChanged}
              placeholder={"Pune,Gurgaon,etc"}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="mobile-number" className="form-label">
              Salary
            </label>
            <input
              name="salary"
              type="number"
              className="form-control"
              placeholder="120000"
              onChange={OnTextChanged}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="dob" className="form-label">
              ApplicationDeadline
            </label>
            <input
              name="applicationDeadline"
              type="date"
              className="form-control"
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
            />
          </div>
        </div>

        <div className="col-6">
          <label htmlFor="vacancies" className="form-label">
            Vacancies
          </label>
          <input
            name="vacancies"
            type="number"
            className="form-control"
            placeholder="10"
            onChange={OnTextChanged}
          />
          <div className="mb-3 mt-3">
            <label htmlFor="workSchedule" className="form-label">
              Work Schedule
            </label>
            <div>
              <select
                class="form-select"
                aria-label="Default select example"
                id="workSchedule"
                name="workSchedule"
                value={job.workSchedule}
                onChange={OnTextChanged}
              >
                <option selected>Select Workschedule</option>
                <option value="FULL_TIME">Full time</option>
                <option value="PART_TIME">Part Time</option>
                <option value="INTERNSHIP">Internship</option>
              </select>
            </div>
          </div>
          <div className="mb-3 mt-3">
            <label htmlFor="depId" className="form-label">
              Department
            </label>
            <div>
              <select
                class="form-select"
                aria-label="Default select example"
                id="depId"
                name="depId"
                value={job.depId}
                onChange={OnTextChanged}
              >
                <option selected>Select Department</option>
                <option value="1">IT</option>
                <option value="2">HR</option>
                <option value="3">Finance</option>
                <option value="4">Marketing</option>
                <option value="5">Sales</option>
                <option value="6">Engineering</option>
                <option value="7">Operations</option>
                <option value="8">Customer Service</option>
                <option value="9">Research and development</option>
                <option value="10">Legal</option>
              </select>
            </div>
          </div>
          <div className="mb-3 mt-3">
            <label htmlFor="experienceRequired" className="form-label">
              Experience Required
            </label>
            <div>
              <select
                class="form-select"
                aria-label="Default select example"
                id="experienceRequired"
                name="experienceRequired"
                value={job.experienceRequired}
                onChange={OnTextChanged}
              >
                <option selected>Experienced Required</option>
                <option value="0">Fresher</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div className="mb-3 mt-3">
            <label htmlFor="description" className="form-label">
              Description
            </label>
            <input
              type="text"
              name="description"
              className="form-control"
              placeholder=""
              onChange={OnTextChanged}
            />
          </div>
      <div className="row">
        <div className="col-2">
          <button
            type="button"
            className="btn shadow-sm"
            style={{ backgroundColor: "#49A646" }}
            onClick={() => {
              createJob();
            }}
          >
            Create Job
          </button>
        </div>
        <div className="col-2">
          <button
            type="button"
            className="btn shadow-sm"
            style={{ backgroundColor: "#F28E13" }}
            onClick={() => {
              resetAllFields();
            }}
          >
            Reset
          </button>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
};
