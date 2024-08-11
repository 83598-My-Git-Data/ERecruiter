import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { getApplicantsByJobId,updateStatus } from "../../services/hr";
import { ToastContainer,toast } from "react-toastify";

export const Applicants = () => {
  /*Use to access the set state from last component*/
  const location = useLocation();

  /*State to store the all applicants*/
  const [applicant, setApplicant] = useState([]);

  /*State to store selected status*/
  const [selectedStatuses, setSelectedStatuses] = useState({});

  const handleStatusChange = async (applicantId) => {
    /*Body to send to the database to update the status of applicant*/
    const requestBody = {
        applicantId: applicantId,
        status: selectedStatuses[applicantId],
        jobId: location.state.jobId 
      };
      await updateStatus(requestBody).then((response)=>{
        toast.success(`Status ${requestBody.status} updated`);
      }).catch((err)=>{toast.error("something happend bad!!")})

  };

  /*Fetch all the details*/
  useEffect(() => {
    const fetchApplicants = async () => {
      await getApplicantsByJobId(location.state.jobId).then((response) => {
        setApplicant(response);
      });
    };
    fetchApplicants();
  }, []);

  return (
    <div className={"mt-2"}>
      <h1>Applicants for {location.state.jobName}</h1>
      <hr />
      <table className="table table-hover">
        <thead className="table-light">
          <tr>
            <th scope="col">Applicant id</th>
            <th scope="col">Applicant name</th>
            <th scope="col">Status</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
          {applicant.map((a) => (
            <tr key={a.applicantId}>
              <td>{a.applicantId}</td>
              <td>{a.applicantName}</td>
              <td>{a.status}</td>
              <td>
              <select
                className="form-control"
                value={selectedStatuses[a.applicantId] || ""}
                onChange={(e) => {
                  setSelectedStatuses(prevStatuses => ({
                    ...prevStatuses,
                    [a.applicantId]: e.target.value
                  }));
                }}
              >
                <option value="" disabled>Select Status</option>
                <option value="INTERVIEW">INTERVIEW</option>
                <option value="REJECTED">REJECTED</option>
                <option value="HIRED">HIRED</option>
              </select>
              <button
                className="btn btn-primary m-2"
                onClick={() => handleStatusChange(a.applicantId)}
                disabled={!selectedStatuses[a.applicantId]}
              >
                Update Status
              </button>
            </td>
            </tr>
          ))}
        </tbody>
      </table>
      <ToastContainer/>
    </div>
  );
};
