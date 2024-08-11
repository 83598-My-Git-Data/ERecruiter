import { useEffect, useState } from "react";
import { getJobList } from "../../services/admin";
function JobList() {
  const [jobList, setJobList] = useState([]);
  useEffect(() => {
    // Use the getJobList function to fetch data when the component mounts
    getJobList()
      .then((response) => {
        // Update the jobList state with the data received from the backend
        setJobList(response);
      })
      .catch((error) => {
        console.error("Error fetching Job list:", error);
        // Handle errors if needed
      });
  }, []);

  return (
    <>
      <h1>Job List</h1>
      <hr />
      <div className="container-fluid">
        <table className="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Job Title</th>
              <th scope="col">HR Name</th>
              <th scope="col">Created On</th>
              <th scope="col">Application Deadline</th>
              <th scope="col">Location</th>
              <th scope="col">Vacancies</th>
              <th scope="col">Status</th>
            </tr>
          </thead>
          <tbody>
            {jobList.map((job) => (
              <tr key={job.jobId}>
                <td>{job.jobId}</td>
                <td>{job.jobTitle}</td>
                <td>{job.firstName+" "+job.lastName}</td>
                <td>{job.jobCreatedDate}</td>
                <td>{job.applicationDeadline}</td>
                <td>{job.location}</td>
                <td>{job.vacancies}</td>
                <td>{job["status"] ? "Active" : "Inactive"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default JobList;
