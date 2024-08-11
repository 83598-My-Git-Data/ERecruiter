import { useEffect, useState } from "react";
import {  FetchAppliedJobs, UnApplyJob } from "../../services/applicantJob"
import "../applicant/ProfilePage/ApplicantProfilePage.css"
import { ToastContainer, toast } from "react-toastify";
function AppliedJobs() {


    const [jobs, setJobs] = useState([])


    function WorkSchedule(workschedule) {
        if (workschedule === "PART_TIME") { return "Part time" }
        if (workschedule === "INTERNSHIP") { return "Internship" }
        if (workschedule === "FULL_TIME") { return "Full time" }
    }


    useEffect(() => {

        FetchAppliedJobs(jobs, setJobs)
    }, [])

    return (<>

        <div className=" row justify-content-center my-3" style={{ backgroundColor: "#F5F5F5" }}>
            <div id="signUpBar" className=" text-center  ">
                <h2>Applied jobs</h2>
            </div>
        </div>

        <div>
            {jobs.map((job) => {
                return <>
                    <div className="container py-1">
                        <div className=" row basicDetailsDiv roundedShadowDiv pt-4">
                            {/* jobtitle */}
                            <div className="row">
                                <div className="py-2 col-10"><span style={{ color: "#9B7ED9", fontSize: 20 }}>{job.jobTitle}</span></div>
                                <div className="col-2  row">
                                    <div className=" row">
                                        
                                        
                                        <div className="col-4 px-3 "><i class="bi bi-hand-index-thumb-fill h2 " onClick={() => { UnApplyJob(job.jobId) }}></i></div>
                                    </div>

                                </div>
                            </div>

                            <div className="row">
                                {/* Experience */}
                                <div className="col-1">

                                    <i class="bi bi-suitcase-lg"></i>
                                    <span style={{ color: "black", fontSize: 16, marginLeft: "10px" }}>{job.experienceRequired + " yrs"}</span>
                                </div>
                                {/* Experience */}
                                <div className="vertical-line col-1" style={{ width: "10px" }}></div>
                                {/* location */}
                                <div className="col-2">
                                    <i class="bi bi-geo-alt "></i>
                                    <span style={{ color: "black", fontSize: 16, marginLeft: "10px" }}>{job.location}</span>
                                </div>
                                {/* location */}
                                <div className="vertical-line col-1" style={{ width: "10px" }}></div>
                                {/* salary */}
                                <div className="col-1">
                                    <i class="bi bi-currency-rupee"></i>
                                    <span style={{ color: "black", fontSize: 16, marginLeft: "10px" }}>{job.salary}</span>
                                </div>
                                {/* salary */}
                                <div className="vertical-line col-1" style={{ width: "10px" }}></div>
                                {/* workSchedule */}
                                <div className="col-2">
                                    <i class="bi bi-hourglass"></i>
                                    <span style={{ color: "black", fontSize: 16, marginLeft: "10px" }}>{WorkSchedule(job.workSchedule)}</span>
                                </div>
                                {/* workSchedule */}
                            </div>
                            {/* row end */}
                            {/* Description */}
                            <div className="row">
                                <div className="col-11" style={{ marginTop: "12px" }}>
                                    <p>{job.description}</p>
                                </div>


                            </div>

                        </div>
                    </div>
                </>
            })
            }
            <ToastContainer />
        </div>


    </>);
}

export default AppliedJobs;