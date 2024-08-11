import { useEffect, useState } from "react";
import { getHrList } from "../../services/admin";
import { COLORS } from "../../value/color";
import { deactivateHr } from "../../services/admin";
import { ToastContainer, toast } from "react-toastify";
function HrList() {
  const [hrList, setHrList] = useState([]);
  const [deactivateMessage, setDeactivateMessage] = useState("");
  useEffect(() => {
    // Use the getHrList function to fetch data when the component mounts
    getHrList()
      .then((response) => {
        // Update the hrList state with the data received from the backend
        setHrList(response);
      })
      .catch((error) => {
        console.error("Error fetching HR list:", error);
        // Handle errors if needed
      });
  }, []);

  // deactivate the hr account and handle response
const deactivateHrAccount = (id) => {
  deactivateHr(id)
    .then((response) => {
      toast.success(`Hr account deactivated: ${id}`);
      // Additional UI updates or actions based on the response
    })
    .catch((error) => {
      console.log(error);
      toast.error(`Failed to deactivate Hr account: ${id}`);
      // Additional error handling or UI updates
    });
};

  return (
    <>
      <h1>HR List</h1>
      <hr />
      <div className="container-fluid">
        <table className="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">FirstName</th>
              <th scope="col">LastName</th>
              <th scope="col">Gender</th>
              <th scope="col">Email</th>
              <th scope="col">Contact</th>
              <th scope="col">Qualification</th>
              <th scope="col">Active Status</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {hrList.map((hr) => (
              <tr key={hr.id}>
                <td>{hr.id}</td>
                <td>{hr.firstName}</td>
                <td>{hr.lastName}</td>
                <td>{hr.gender}</td>
                <td>{hr.email}</td>
                <td>{hr.phoneNumber}</td>
                <td>{hr.qualification}</td>
                <td>{hr["status"] ? "Active" : "Inactive"}</td>
                <td>
                 {hr["status"] ? <button
                    className={"btn"}
                    style={{
                      backgroundColor: COLORS.orange,
                      fontWeight: 900,
                      width: 200,
                    }}
                    onClick={() => deactivateHrAccount(hr.id)}
                  >
                    Deactivate
                  </button>:
                  "Deactivated Account"
                  }
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <ToastContainer/>
    </>
  );
}

export default HrList;
