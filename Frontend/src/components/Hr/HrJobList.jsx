import { useEffect, useState } from "react";
import { getAllJobs, deactivateJobById } from "../../services/hr";
import { ToastContainer, toast } from "react-toastify";
import { Link } from "react-router-dom";
export const HRJobList = () => {
  const [jobs, setJobs] = useState([]);

  /*Deactivate  job by id*/
  const deactivateJob = (jobId) => {
    deactivateJobById(jobId)
      .then((response) => {
        toast.success(jobId + ":job deactivated!!");
      })
      .catch((err) => {
        toast.error("something happened bad!");
      });
  };

  /*This will get all list when the page will load*/
  useEffect(() => {
    const fetchAllJobs = async () => {
      await getAllJobs()
        .then((response) => {
          setJobs(response);
          console.log(response.data[0]);
        })
        .catch((err) => {});
    };
    fetchAllJobs();
  }, []);

  return (
    <div className={"mt-2"}>
      <h1>Job List</h1>
      <hr></hr>
      <div className="row">
        {jobs.map((job) => (
          <div className="col-3 m-1" key={job.jobId}>
            <div className="card m-2">
              <div className="card-body">
                <h5 className="card-title">{job.jobTitle}</h5>
                <p className="card-text">
                  <strong>Experience Required:</strong> {job.experienceRequired}
                </p>
                <p className="card-text">
                  <strong>Work Schedule:</strong> {job.workSchedule}
                </p>
                <p className="card-text">
                  <strong>Salary:</strong> {job.salary}
                </p>
                <p className="card-text">
                  <strong>Application Deadline:</strong>{" "}
                  {job.applicationDeadline}
                </p>
                <p className="card-text">
                  <strong>Qualification Required:</strong> {job.qualification}
                </p>
                <p className="card-text">
                  <strong>Department:</strong> {job.departmentName}
                </p>
                <p className="card-text">
                  <strong>Vacancies:</strong> {job.vacancies}
                </p>
                <p className="card-text">
                  <strong>Status:</strong>{" "}
                  {job["status"] ? "Active" : "Inactive"}
                </p>
                <div className="card-footer text-left">
                  <div className="row">
                    <div className="col-6">
                      <Link
                        className="btn btn-success btn-block m-2"
                        to="/hr/update-job"
                        state={{ sendJob: job }}
                      >
                        Update
                      </Link>
                    </div>
                    <div className="col-6">
                      {job["status"] && (
                        <button
                          className="btn btn-danger btn-block m-2"
                          onClick={() => {
                            deactivateJob(job.jobId);
                          }}
                        >
                          Deactivate
                        </button>
                      )}
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-12">
                      <Link
                        className="btn btn-info btn-block m-2"
                        to={`/hr/applicants`}
                        state={
                          {jobId:job.jobId,jobName:job.jobTitle}
                        }
                      >
                        Applicants
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
      <ToastContainer />
    </div>
  );
};
