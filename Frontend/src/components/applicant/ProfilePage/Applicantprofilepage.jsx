
import ProfilePicAlternate from "../../../assets/images/ProfilePicAlternate.png";
import { useState, useEffect } from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { BASE_URL } from "../../../services/helper";
//Importing service methoda
import {
  FetchUserDetailsInfo, FetchAddressDetails, FetchProfileInfo,
  FetchSkills, DeleteSkill, FetchSkillList, AddSkill, FetchEducations,
  ExtractYear, FetchSchoolingDetails, FetchProjectDetails,
  FetchEmploymentDetails, DeleteResume, EditProject, UpdateProject,
  DeleteProject, UpdateEmployements, EditEmployment, DeleteEmployment, uploadImage, upDateImage, removeImage
} from '../../../services/applicant'

import { logout } from "../../../services/helper"
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import '../ProfilePage/ApplicantProfilePage.css'



function ProfilePage() {
  //base url for applicant contoller
  const baseurl = BASE_URL+"/";


  //useState for User Details api
  const [userDetails, setUserDetails] = useState({ firstName: "", lastName: "", gender: "", email: "", phoneNumber: "", dob: "" })
  //useState for Address api
  const [address, setAddress] = useState({ permanentAddress: "", pincode: "", state: "", country: "" })
  //useState for Profile info api
  const [profileInfo, setProfileInfo] = useState({ emailIdVerifyStatus: false, mobileNumVerifyStatus: false, resumeLink: "", resumeHeadLine: "", profileSummary: "", profilePictureLink: "", maritalStatus: "", noticePeriod: "" })
  //useState for Resume upload api
  const [file, setFile] = useState(null);
  //usetsate for applicant skill
  const [skills, setSkills] = useState([]);
  //usestate for skill list for dropdown
  const [skillList, setSkillList] = useState([]);
  //usestate for applicant educations
  const [education, setEducation] = useState([]);
  //usestate for applicant schooling details
  const [schooling, setSchooling] = useState();
  //usestae for applicant projects 
  const [projects, setProjects] = useState([])
  //usestate for posting project
  const [project, setProject] = useState({
    id: 0,
    projectTitle: "",
    projectStatus: false,
    projectStartDate: "",
    projectEndDate: "",
    projectDescription: ""
  })
  //usestate for applicant employments
  const [employments, setEmployments] = useState([])
  //useState for add and update bit
  const [projectbit, setProjectBit] = useState(false)
  //usestate for employement input
  const [employment, setEmployment] = useState(
    {
      id: 0,
      currentlyEmployed: true,
      employementType: "",
      experienceYears: 0,
      experienceMonths: 0,
      currentCompanyName: "",
      previousCompanyName: "desination",
      currentDesignation: "",
      previousDesignation: "company",
      jobProfile: "",
      department: "",
      currentSalary: 0
    }
  )
  //usestate for status
  const [employmentBit, setEmploymentBit] = useState(false)
  
   //state to hold the image
   const [image, setImage] = useState(null);

   //upload image
   const uploadProfileImage = () => {
     uploadImage(image)
       .then((response) => {
         toast.success("Image uploaded successfully");
       })
       .catch(() => {
         toast.error("something happened bad")
       });
   };
 
    //upadate image
    const updateProfileImage = () => {
     upDateImage(image)
       .then((response) => {
         toast.success("Image updated successfully");
       })
       .catch(() => {
         toast.error("something happened bad")
       });
   }
 
   //remove image 
   const removeProfilePic=()=>{
     removeImage().then((response=>{
       toast.success("image removed successfully")
     })).catch(()=>{
       toast.error("something happened bad")
     })
   }

  //for employment inputs
  const OnTextEmploymentChanged = (args) => {
    var copyOfData = { ...employment };
    copyOfData[args.target.name] = args.target.value;
    setEmployment(copyOfData);
  };


  //for project inputs
  const OnTextProjectChanged = (args) => {
    var copyOfData = { ...project };
    copyOfData[args.target.name] = args.target.value;
    setProject(copyOfData);
  };

  //handle project status
  const handleSTatusChange = (event) => {
    const status = event.target.value; // Get the selected gender value
    setProject({
      ...project,
      projectStatus: status, // Update the gender field in the state
    });
  };


  //to fetch data on load
  useEffect(() => {

    FetchUserDetailsInfo(userDetails, setUserDetails);
    FetchAddressDetails(address, setAddress);
    FetchProfileInfo(profileInfo, setProfileInfo);
    FetchSkills(skills, setSkills);
    FetchSkillList(skillList, setSkillList)
    FetchEducations(education, setEducation)
    FetchSchoolingDetails(schooling, setSchooling)
    FetchProjectDetails(projects, setProjects)
    FetchEmploymentDetails(employments, setEmployments)
  }, []);


  //set resume in file on upload
  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  //send file on application server
  const handleSubmit = async () => {
    const formData = new FormData();
    formData.append('file', file);
    try {
      await axios.post(baseurl + 'applicant/upload-resume', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }

      }).then(() => {
        toast.success("Resume uploaded successfully")
      })
      console.log('File uploaded successfully');
    } catch (error) {
      console.error('Error uploading file:', error.message);

      toast.error("Resume could not be uploaded")
    }
  };




  const navigate = useNavigate();


  return (<>
    <nav className="navbar navbar-expand-lg  background">
      <Link className="navbar-brand" to="/home">
        Get Hired
      </Link>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav ms-auto">
          <li className="nav-item ">
            <NavLink className="nav-link items" to={"/applicant"}>
              HOME
            </NavLink>
          </li>
          <li className="nav-item ">
            <NavLink className="nav-link items" to={"/jobs"}>
              JOBS
            </NavLink>
          </li>
          <li className="nav-item ">
            <button className="nav-link items" onClick={() => { logout(navigate) }}>LOG OUT</button>
          </li>
        </ul>
      </div>
    </nav>


    <div className="mainDiv">

      <div className="container  containerDiv pt-3">

        <div className="row ">

          <div className="  basicDetailsDiv roundedShadowDiv pt-4">
            {/* div to keep every div in a row  */}
            <div className="row">

              {/* this is IMAGE div */}
              <div className={"col-md-4"}>
            <div className={"border m-2 p-2 w-auto"}>
              {profileInfo.profilePictureLink === "deleted" ? (
                /*Upload image */
                <div>
                  <div className={"mb-4 d-flex justify-content-center"}>
                    <img
                      id="selectedImage"
                      src={ProfilePicAlternate}
                      alt="example placeholder"
                      style={{ width: 300 }}
                    />
                  </div>
                  <div class={"d-flex justify-content-center"}>
                    <div className="">
                      <label className={"form-label"}>Upload Image</label>
                      <input
                        type="file"
                        className={"form-control btn btn-rounded"}
                        onChange={(event) => {
                          setImage(event.target.files[0]);
                        }}
                      />
                    </div>
                  </div> 
                  {image !== null ? (
                    <div className={"text-center"}>
                      <button
                        className={"btn m-2"}
                        onClick={() => {
                          uploadProfileImage();
                        }}
                      >
                        Upload Image
                      </button>
                    </div>
                  ) : (
                    <>
                    </>
                  )}
                </div>
              ) : (
                /*Update Image*/
                <>
                <div className={"mb-4 d-flex justify-content-center"}>
                    <img
                      id="selectedImage"
                      src={profileInfo.profilePictureLink}
                      alt="example placeholder"
                      style={{ width: 300 }}
                    />
                  </div>
                  <div class={"d-flex justify-content-center"}>
                    <div className="">
                      <label className={"form-label"}>Update Image</label>
                      <input
                        type="file"
                        className={"form-control btn btn-rounded"}
                        onChange={(event) => {
                          setImage(event.target.files[0]);
                        }}
                      />
                    </div>
                    <div className={"text-center"}>
                      <button
                        className={"btn btn-danger m-2"}
                        onClick={() => {
                          removeProfilePic();
                        }}
                      >
                        Remove Image
                      </button>
                    </div>  
                  </div>
                  {image !== null ? (
                    <div className={"text-center"}>
                      <button
                        className={"btn m-2"}
                        onClick={() => {
                          updateProfileImage();
                        }}
                      >
                        Upadte Image
                      </button>
                    </div>
                  ) : (
                    <></>
                  )}
                </>
              )}
            </div>
          </div>

              {/* this is user detail div */}
              {/* {userDetails.firstName}{userDetails.lastName} */}
              <div className="col-7">
                <div className="px-3"><span className="name">{userDetails.firstName} {userDetails.lastName}</span><i style={{ cursor: "pointer" }} class="bi bi-pencil h4 pencil" ></i> </div>
                <hr />
                <div className="row">
                  <div className="col-5">
                    <div className="iconPadding"><i class="bi bi-geo-alt iconPadding"></i>{address?.state ?? "Enter address"}</div>
                    <div><i className="bi bi-calendar2-event iconPadding"></i>
                      {profileInfo.noticePeriod === "FIFTEEN_DAYS_OR_LESS" ? "15 Days or less of notice period" :
                        profileInfo.noticePeriod === "ONE_MONTH" ? "One month of notice period" :
                          profileInfo.noticePeriod === "TWO_MONTHS" ? "Two months of notice period" :
                            profileInfo.noticePeriod === "THREE_MONTHS" ? "Three months of notice period" :
                              "Three months of more notice period"
                      }

                    </div>
                  </div>
                  <div className="col-1 vertical-line  vertical-line-basic"></div>
                  <div className="col-5 paddingMargin">
                    <div className="iconPadding">
                      <i className="bi bi-telephone iconPadding"></i>
                      {userDetails.phoneNumber}
                    </div>
                    <div>
                      <i className="bi bi-envelope iconPadding"></i>
                      {userDetails.email}
                    </div>

                  </div>
                </div>
              </div>
              {/* userDetails div */}
              <div>



              </div>
            </div>



          </div>


        </div>
        {/* resume card */}
        <div className="row my-1">
          <div className="  basicDetailsDiv roundedShadowDiv pt-4">
            <div className="row">
              {/* resume headline */}
              <div className="col-6">
                <div><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>Resume headline</span> <i style={{ cursor: "pointer" }} class="bi bi-pencil h5 pencil" ></i></div>
                {
                  profileInfo.resumeHeadLine === "0" ?
                    <div className="py-3" >
                      <span>It is the first thing recuiters notice in your profile. Write concisely what makes you unique and right person for the job you are looking for .</span>
                      <div className="py-3"><h4>Write you resume headlines</h4></div>
                    </div>
                    :
                    <div className="m-2">{profileInfo.resumeHeadLine}</div>

                }
              </div>
              <div className="vertical-line col-1" style={{ width: "80px" }}></div>
              {/* resume upload */}
              <div className="row col-5">

                {profileInfo.resumeLink === "deleted" ?
                  //if user hasn't uploaded resume
                  <div>
                    <span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>Resume</span> <br />
                    <span style={{ fontSize: "14px" }} className="">A resume is the most important document recruiter look for. Recruiters generally do no look at profiles without resume.</span>

                    <div className="col-6">
                      <div className="row">
                        <div className="py-3"><input className="form-control" type="file" onChange={handleFileChange} /></div>
                        <div className="py-2">Supported formats : PDF</div>
                      </div>
                    </div>

                    <div className="col-6 py-1">

                      <button className="  rounded-button rounded-button:hover" onClick={() => { handleSubmit() }}>Upload</button>
                    </div>
                  </div> :
                  //if user has uploaded resume
                  <div>

                    <div className="row">
                      <div className="col-6"><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>Resume</span> </div>
                      <div className="col-6 py-2"><span onClick={() => { DeleteResume() }} style={{ cursor: "pointer", color: "blue" }} className="text-decoration-none">Delete</span></div>

                    </div>

                    <span style={{ fontSize: "14px" }} className="paddingMargin">A resume is the most important document recruiter look for. Recruiters generally do no look at profiles without resume.</span>

                    <div className="col-12">
                      <div className="dotted-border-div row justify-content-center">
                        <div className="col-8">
                          <Link to={profileInfo.resumeLink} target="_blank" rel="noopener noreferrer" className="rounded-button rounded-button:hover text-decoration-none">DOWNLOAD-RESUME</Link></div>

                      </div>
                    </div>

                  </div>
                }


              </div>
            </div>

          </div>
          {/* Below closing div tag is of resume card */}
        </div>









        {/* Below closing div tag is of Skill card */}
        <div className="row my-1">
          <div className="  basicDetailsDiv roundedShadowDiv pt-4">

            {/* for keeping in a row */}
            <div className="row">

              {/* Key skills */}
              <div className="col-6">
                <div className="py-1 px-1"><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>Key skills</span> </div>
                <div>{skills.map((skill) => { return (<button key={skill.skillId} style={{ margin: "3px" }} onClick={() => { DeleteSkill(skill.skillId, skills, setSkills) }} className="rounded-button rounded-button:hover text-decoration-none ">{skill.name}</button>) })}</div>
              </div>








              {/* Veritcle line */}
              <div className="vertical-line col-1" style={{ width: "80px" }}></div>







              {/* Add Skils */}
              <div className="col-5">
                <div><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>Add skills</span> </div>
                <div>
                  <select class="form-select" aria-label="Default select example" id="skills" onChange={(e) => { AddSkill(e.target.value, skills, setSkills) }}>
                    <option selected>Select skill to add</option>

                    {skillList.map((skill) => { return (<option value={skill.skillId} >{skill.name}</option>) })}
                  </select>
                </div>
              </div>
            </div>
          </div>
          {/* Below closing div tag is of Skill card */}
        </div>









        {/* Below opening div tag is of education card */}
        <div className="row my-1">
          <div className="  basicDetailsDiv roundedShadowDiv pt-4">
            {/* for keeping in a row */}
            <div className="row">

              {/* HigherEducation  */}
              <div className="col-6">
                <div className="py-1 px-1"><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>Higher Education</span> </div>
                {education.map((edu) => {
                  return <>
                    <div className="mt-2">
                      <span style={{ color: "black", fontSize: "20px", paddingLeft: "5px", paddingRight: "5px" }} className="" >{edu.course}</span><span style={{ color: "black", fontSize: "17px" }}>/ {edu.specialization}</span> <br />
                      <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} className="" >{edu.university}</span>
                      <div className="row col-6">
                        <div className="col-5 " style={{ paddingRight: "0px", marginRight: "12px" }} >
                          <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} >{ExtractYear(edu.courseStartDate)} to {ExtractYear(edu.courseEndDate)}</span>
                        </div>
                        <div className="vertical-line " style={{ width: "1px" }}></div>
                        <div className="col-1 d-flex justify-content-start" >
                          <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }}  >{edu.educationType}</span>
                        </div>
                      </div>
                    </div>
                  </>
                })}

              </div>







              {/* Veritcle line */}
              <div className="vertical-line col-1" style={{ width: "80px" }}></div>






              {/* Schooling */}
              <div className="col-5">
                <div><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>Schooling</span> </div>
                {/* Class 12 div */}
                <div className="mt-2">
                  <span style={{ color: "black", fontSize: "20px", paddingLeft: "5px", paddingRight: "5px" }} className="" >Class XII</span> <br />
                  <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} className="" >{schooling?.class12Board ?? 'Enter class XII board name  |  '}</span>
                  <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} >{ExtractYear(schooling?.class12PassingYear ?? "Enter class XII passing year")} </span> <br />
                  <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} className="" >{schooling?.class12Marks +"%"?? "Enter class XII marks"}</span>
                </div>
                <hr />
                {/* class 10 div */}
                <div className="mt-2">
                  <span style={{ color: "black", fontSize: "20px", paddingLeft: "5px", paddingRight: "5px" }} className="" >Class X</span> <br />
                  <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} className="" >{schooling?.class10Board ?? "Enter class X board name   | "}</span>
                  <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} >{ExtractYear(schooling?.class10PassingYear ?? "Enter class X passing year")} </span> <br />
                  <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} className="" >{schooling?.class10Marks +"%"?? "Enter class X marks"}</span>
                </div>
              </div>
            </div>
          </div>
          {/* Below closing div tag is of education card */}
        </div>







        {/* below div is of project card */}
        <div className="row my-1">
          <div className="  basicDetailsDiv roundedShadowDiv pt-4">
            {/* for keeping in a row */}
            <div className="row">

              {/* Project  */}
              <div className="col-6">
                <div className="row">
                  <div className=" col-2 px-3"><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>Projects</span> </div>

                </div>

                {projects.map((proj) => {
                  return <>
                    <div className="mt-2">
                      <div className="row">
                        <div className="col-10 px-3">
                          <span style={{ color: "black", fontSize: "20px", paddingLeft: "1px", paddingRight: "5px" }} className="" >{proj.projectTitle}</span><br />
                        </div>
                        <div className="col-2 py-">
                          <div className="row">
                            <div className="col-1">
                              <i style={{ cursor: "pointer" }} onClick={() => { DeleteProject(proj.id, projects, setProject); }} class="bi bi-trash"></i>
                            </div>
                            <div className="col-1">
                              <i style={{ cursor: "pointer" }} onClick={() => { EditProject(proj, project, setProject, setProjectBit) }} class="bi bi-pencil h6 pencil" ></i>
                            </div>
                          </div>


                        </div>

                      </div>



                      <div className="col-5 " style={{ paddingRight: "0px", marginRight: "12px" }} >
                        <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} >{ExtractYear(proj.projectStartDate)} to {ExtractYear(proj.projectEndDate)}</span>
                      </div>

                      <div className="col12 d-flex justify-content-start" >
                        <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }}  >{proj.projectDescription}</span>

                      </div>
                      <hr />

                    </div>
                  </>
                })}

              </div>





              {/* Veritcle line */}
              <div className="vertical-line col-1" style={{ width: "80px" }}></div>






              {/* add projects */}
              <div className="col-5">
                <div><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>{projectbit ? "Update employment" : "Add employment"}</span> </div>

                <form>
                  {/* Project title */}
                  <div className="form-group">
                    <br />
                    <span style={{ color: "black", fontSize: 18 }}>Project title</span>
                    <input
                      type="text"
                      className="form-control"
                      id="projectTitle"
                      name="projectTitle"
                      value={project.projectTitle}
                      placeholder="Enter project title"
                      autoComplete="off"
                      onChange={OnTextProjectChanged}
                    />
                  </div>
                  {/* Project title */}
                  <br />

                  {/* Project date */}
                  <div className="row">
                    {/* Project start date */}
                    <div className="col-6 form-group">
                      <span style={{ color: "black", fontSize: 18 }}>Start date</span>
                      <input
                        type="date"
                        className="form-control"
                        id="projectStartDate"
                        name="projectStartDate"
                        value={project.projectStartDate}
                        autoComplete="off"
                        onChange={OnTextProjectChanged}
                      />
                    </div>
                    {/* Project end date */}

                    <div className="col-6 form-group">
                      <span style={{ color: "black", fontSize: 18 }}>End date</span>
                      <input
                        type="date"
                        className="form-control"
                        id="projectEndDate"
                        name="projectEndDate"
                        value={project.projectEndDate}
                        autoComplete="off"
                        onChange={OnTextProjectChanged}
                      />
                    </div>

                  </div>
                  {/* Project date */}
                  <br />
                  {/* Project description */}
                  <div className="form-group">
                    <span style={{ color: "black", fontSize: 18 }}>Project description</span>
                    <textarea

                      type="textarea"
                      className="form-control"
                      id="myTextarea"
                      name="projectDescription"
                      value={project.projectDescription}
                      placeholder="Enter project description"
                      autoComplete="off"
                      onChange={OnTextProjectChanged}
                    />
                  </div>
                  {/* Project description */}
                  <br />
                  {/* update/add button */}
                  <button
                    type="button"

                    className="btn btn-primary"
                    onClick={() => {

                      UpdateProject(project, setProject, projectbit, setProjectBit, projects, setProjects)
                    }}
                    style={{ backgroundColor: "#9B7ED9", width: 110, height: 40 }}
                  >
                    {projectbit ? "Update" : "Add"}
                  </button>

                </form>
              </div>
            </div>
          </div>
          {/* below closeing div is of project card */}
        </div>






        {/* below div is of Employement card */}
        <div className="row my-1">
          <div className="  basicDetailsDiv roundedShadowDiv pt-4">
            {/* for keeping in a row */}
            <div className="row">

              {/* Project  */}
              <div className="col-6">
                <div className="row">
                  <div className=" col-2 px-3"><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>Employment</span> </div>

                </div>

                {employments.map((emp) => {
                  return <>
                    <div className="mt-2">
                      <div className="row">
                        <div className="col-10 px-3">
                          <span style={{ color: "black", fontSize: "22px", paddingLeft: "1px", paddingRight: "5px" }} className="" >{emp.currentDesignation}</span><br />
                        </div>
                        <div className="col-2 py-">
                          <div className="row">
                            {/* <div className="col-1">
                              <i style={{ cursor: "pointer" }} onClick={() => { DeleteEmployment(emp.id, employments, setEmployments); }} class="bi bi-trash"></i>
                            </div> */}
                            <div className="col-1">
                              <i style={{ cursor: "pointer" }} onClick={() => { EditEmployment(emp, employment, setEmployment, setEmploymentBit) }} class="bi bi-pencil h6 pencil" ></i>
                            </div>
                          </div>


                        </div>

                      </div>
                      {/* company name */}
                      <div className="col12 d-flex justify-content-start" >
                        <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }}  >{emp.currentCompanyName}</span>

                      </div>

                      <div className="row">
                        <div className="col-4 " style={{ paddingRight: "0px", marginRight: "12px" }} >
                          <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }} >{emp.experienceYears} years and  {emp.experienceMonths} months</span>
                          {/* Veritcle line */}

                        </div>
                        <div className="vertical-line col-1" style={{ width: "80px" }}></div>
                        <div className="col-3">
                          <span style={{ color: "black", fontSize: "16px", paddingLeft: "5px", paddingRight: "5px" }}  >{emp.employementType}</span>
                        </div>
                      </div>


                      <hr />

                    </div>
                  </>
                })}

              </div>



              {/* Veritcle line */}
              <div className="vertical-line col-1" style={{ width: "80px" }}></div>





              {/* add Employment */}
              <div className="col-5">
                <div><span className="paddingMargin" style={{ color: "#9B7ED9", fontSize: "25px" }}>{projectbit ? "Update employment" : "Add employment"}</span> </div>

                <form>
                  {/* drop down */}
                  <div className="row">

                    {/* Employemt type */}
                    <div className="col-6 py-1">
                      <div><span style={{ color: "black", fontSize: 18 }}>Employment type</span></div>
                      <div>
                        <select class="form-select" aria-label="Default select example" id="employementType" name="employementType" value={employment.employementType} onChange={OnTextEmploymentChanged}>
                          <option selected>Select employment type</option>
                          <option value="Full time" >Full time</option>
                          <option value="Part Time" >Part Time</option>
                          <option value="Internship" >Internship</option>
                          <option value="Temporary" >Temporary</option>
                          <option value="Contract" >Contract</option>
                        </select>
                      </div>
                    </div>


                    {/* Department */}
                    <div className="col-6 py-1">
                      <div><span style={{ color: "black", fontSize: 18 }}>Department</span></div>
                      <div>
                        <select class="form-select" aria-label="Default select example" id="department" name="department" value={employment.department} onChange={OnTextEmploymentChanged}>
                          <option selected>Select department</option>
                          <option value="Product management" >Product management</option>
                          <option value="DevOps" >DevOps</option>
                          <option value="Quality assurance" >Quality assurance</option>
                          <option value="Technical support" >Technical support</option>
                          <option value="Human resource" >Human resource</option>
                          <option value="Other" >Other</option>
                        </select>
                      </div>
                    </div>
                  </div>

                  <br />

                  {/* experience drop downs */}
                  <div className="row">

                    {/* year */}
                    <div className="col-6 py-1">
                      <div><span style={{ color: "black", fontSize: 18 }}>Experience in years</span></div>
                      <div>
                        <select class="form-select" aria-label="Default select example" id="experienceYears" name="experienceYears" value={employment.experienceYears} onChange={OnTextEmploymentChanged}>

                          <option selected>Select Exp in years</option>
                          <option key={0} value={0}>0</option>

                          {[...Array(30).keys()].map(index => (
                            <option key={index + 1} value={index + 1}>{index + 1}</option>
                          ))}


                        </select>
                      </div>
                    </div>


                    {/* month */}
                    <div className="col-6 py-1">
                      <div><span style={{ color: "black", fontSize: 18 }}>Experience in months</span></div>
                      <div>
                        <select class="form-select" aria-label="Default select example" name="experienceMonths" id="experienceMonths" value={employment.experienceMonths} onChange={OnTextEmploymentChanged}>
                          <option selected>Select exp in months</option>
                          <option key={0} value={0}>0</option>
                          {[...Array(12).keys()].map(index => (
                            <option key={index + 1} value={index + 1}>{index + 1}</option>
                          ))}
                        </select>
                      </div>
                    </div>
                  </div>








                  {/* designation */}
                  <div className="form-group">
                    <br />
                    <span style={{ color: "black", fontSize: 18 }}>Designation</span>
                    <input
                      type="text"
                      className="form-control"
                      id="currentDesignation"
                      name="currentDesignation"
                      value={employment.currentDesignation}
                      placeholder="Enter current designation"
                      autoComplete="off"
                      onChange={OnTextEmploymentChanged}
                    />
                  </div>


                  {/* current company */}
                  <div className="form-group">
                    <br />
                    <span style={{ color: "black", fontSize: 18 }}>Company</span>
                    <input
                      type="text"
                      className="form-control"
                      id="currentCompanyName"
                      name="currentCompanyName"
                      value={employment.currentCompanyName}
                      placeholder="Enter current company name"
                      autoComplete="off"
                      onChange={OnTextEmploymentChanged}
                    />
                  </div>

                  {/* current salary */}
                  <div className="form-group">
                    <br />
                    <span style={{ color: "black", fontSize: 18 }}>Salary</span>
                    <input
                      type="text"
                      className="form-control"
                      id="currentSalary"
                      name="currentSalary"
                      value={employment.currentSalary}
                      placeholder="Enter monthly salary "
                      autoComplete="off"
                      onChange={OnTextEmploymentChanged}
                    />
                  </div>


                  <br />
                  {/* Profile description */}
                  {/* <div className="form-group">
                    <span style={{ color: "black", fontSize: 18 }}>Profile description</span>
                    <textarea

                      type="textarea"
                      className="form-control"
                      id="myTextarea"
                      name="profileInfo"
                      value={employment.jobProfile}
                      placeholder="Enter project description"
                      autoComplete="off"
                      onChange={OnTextEmploymentChanged}
                    />
                  </div> */}
                  {/* Profile description */}
                  {/* <br /> */}
                  {/* update/add button */}
                  <button
                    type="button"

                    className="btn btn-primary"
                    onClick={() => {

                      UpdateEmployements(employment, setEmployment, employmentBit, setEmploymentBit, employments, setEmployments)
                    }}
                    style={{ backgroundColor: "#9B7ED9", width: 110, height: 40 }}
                  >
                    {employmentBit ? "Update" : "Add"}
                  </button>

                </form>
              </div>

            </div>
          </div>
        </div>
      </div>
      <ToastContainer />
    </div>
  </>
  );
}

export default ProfilePage;

