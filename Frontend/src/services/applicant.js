import axios from "axios";
import { toast } from "react-toastify";
import { BASE_URL } from "./helper";
//base url for applicant contoller
const baseurl = BASE_URL+"/";



//fetch user details
export function FetchUserDetailsInfo(userDetails,setUserDetails){
  
  axios.get(baseurl+"applicant/user-detail").then((response)=>{

    setUserDetails(response.data)
    
    console.log("RESPONSE :"+userDetails.firstName+userDetails.lastName+userDetails.email);
  
  }).catch((error)=>{ 
  
    console.log("ERROR :"+error);
  
  })
}


//ftech address details
export function FetchAddressDetails(address,setAddress){
  axios.get(baseurl+"applicant/address").then((response)=>{
    setAddress(response.data)
    console.log("Address :"+ address.permanentAddress+address.pincode+address.state+address.country);
  }).catch((error)=>{
    console.log("ERROR :"+ error);
  })
}


//fetch Profile info
export function FetchProfileInfo(profileInfo,setProfileInfo){
  axios.get(baseurl+"applicant/profile-info").then((response)=>{
    setProfileInfo(response.data)
    console.log("Profile Info :"+ profileInfo.emailIdVerifyStatus +profileInfo.mobileNumVerifyStatus+profileInfo.resumeLink+
    profileInfo.resumeHeadLine+profileInfo.profileSummary+profileInfo.profilePictureLink+profileInfo.maritalStatus+
    profileInfo.noticePeriod);
  }).catch((error)=>{
    console.log("PROFILE DATA ERROR :"+ error);
  })
}


//Delete resume
export function DeleteResume(){
  axios.delete(baseurl+"applicant/remove-resume").then((response)=>{
    if(response){
      toast.warn("Resume deleted")
    }
  }).catch((error)=>{
    if(error){
      toast.error("Resume could not be deleted")
    }
  })
}


//fetch applicant skills
export function FetchSkills(skills,setSkills){
  axios.get(baseurl+"applicant/skills").then((response)=>{
    setSkills(response.data)
    
    
    
  }).catch((error)=>{
    console.log("Skill error"+error);
  })
}


//delete skill
export function DeleteSkill(skillId,skills,setSkills){
  axios.delete(baseurl+"applicant/skills/"+skillId).then((response)=>{
    toast.warn("Skill deleted");
    FetchSkills(skills, setSkills);
  }).catch((error)=>{console.log(error);})
}


//fetch Skill list to add 
export function FetchSkillList(skillList,setSkillList){
  axios.get(baseurl+"skill/skill-list").then((response)=>{
    setSkillList(response.data)
    
    
    
  }).catch((error)=>{
    console.log("SkillList error"+error);
  })
}


//add skills to applicant profile
export function AddSkill(skillId,skills,setSkills){
  axios.put(baseurl+"applicant/add-skill/"+skillId).then((response)=>{ if(response){ toast.success("Skill added successfully");
    FetchSkills(skills, setSkills);}}).catch((error)=>{ console.log(error);})
}

//fetch educations details
export function FetchEducations(education ,setEducation){
  axios.get(baseurl+"applicant/education-details").then((response)=>{setEducation(response.data); 
    }).catch((err)=>{console.log(err);})
}


//extract year from date 
export function ExtractYear(dateString){
  
  const parts = dateString.split("-");
  return parts[0]; 


}


//fetch schooling details
export function FetchSchoolingDetails(schooling,setSchooling){
  axios.get(baseurl+"applicant/schooling").then((response)=>{setSchooling(response.data);}).catch((err)=>{console.log(err);})
}

//fetch project details
export function FetchProjectDetails(projects,setProjects){
  axios.get(baseurl+"applicant/project-details").then((response)=>{setProjects(response.data);
    console.log("Project :"+projects);}).catch((err)=>{console.log(err);})
}

export function UpdateResumeHedline(resumeheadline,setProfileInfo){
  axios.put(baseurl+"applicant/headline",resumeheadline).then((response)=>{
    console.log(response.data);
    setProfileInfo(resumeheadline);
  }).catch((err)=>{
    console.log(err);
  })
}

//fetch employment details
export function FetchEmploymentDetails(employments,setEmployments){
  axios.get(baseurl+"applicant/employment").then((response)=>{setEmployments(response.data);
    console.log("Employments :"+employments);}).catch((err)=>{console.log(err);})
}

export function EditProject(proj,project,setProject,setProjectBit){
  setProject(proj)
  setProjectBit(true) 
}

export function UpdateProject(project,setProject,projectBit,setProjectBit,projects,setProjects){
  if(projectBit){
    axios.put(baseurl+"applicant/project",project).then((response)=>{
      console.log(response.data);
      setProjectBit(false)
      clearProjectInput(setProject)
      FetchProjectDetails(projects,setProjects)
    }).catch((err)=>{console.log(err);})
  }else{
    axios.post(baseurl+"applicant/project",project).then((response)=>{
      console.log(response.data);
      FetchProjectDetails(projects,setProjects)
      clearProjectInput(setProject)
      
    }).catch((err)=>{console.log(err);})
  }
  
}

export function DeleteProject(projectId,projects, setProject){
  axios.delete(baseurl+"applicant/project/"+projectId).then((response)=>{
    console.log(response.data);
    toast.warn("Project Deleted");
    FetchProjectDetails(projects, setProject)
  }).catch((err)=>{console.log(err);})
}

function clearProjectInput(setProject){
  
  setProject({
    id: 0,
    projectTitle: "",
    projectStatus: false,
    projectStartDate: "",
    projectEndDate: "",
    projectDescription: ""
  })
}

// export function FetchEmployements(employments,setEmployments){
//   axios.get(baseurl+"employment").then((response)=>{
//     setEmployments(response.data)
//   }).catch((err)=>{console.log(err);})
// }


  export function UpdateEmployements(employment,setEmployment,employmentBit,setEmploymentBit,employments,setEmployments){
    if(employmentBit){
      console.log(employment);
      debugger;
    axios.put(baseurl+"applicant/employment",employment).then((response)=>{
      console.log(response.data);
      setEmploymentBit(false)
      FetchEmploymentDetails(employments, setEmployments)
      clearEmploymentInput(setEmployment)
     
    }).catch((err)=>{console.log(err);})
  }else{
    console.log(employment);
    debugger;
    axios.post(baseurl+"applicant/employment",employment).then((response)=>{
      console.log(response.data);
      FetchEmploymentDetails(employments, setEmployments)
      clearEmploymentInput(setEmployment)
      
    }).catch((err)=>{console.log(err);})  
  }
}

export function DeleteEmployment(empId,employments,setEmployments){
  axios.delete(baseurl+"applicant/employment/"+empId).then((resp)=>{
    toast.warn("Employment deleted")
    FetchEmploymentDetails(employments, setEmployments)
  }).catch((err)=>{console.log(err);})
}

function clearEmploymentInput(setEmployment){
  setEmployment({
    id: 0,
      currentlyEmployed: true,
      employementType: "",
      experienceYears: 0,
      experienceMonths: 0,
      currentCompanyName: "",
      previousCompanyName: "",
      currentDesignation: "",
      previousDesignation: "",
      jobProfile: "",
      department: "",
      currentSalary: 0
  })
}

export function EditEmployment(emp,employment,setEmployment,setEmploymentBit){
  setEmployment({
    
      id: emp.id,
      currentlyEmployed: emp.currentlyEmployed,
      employementType: emp.employementType,
      experienceYears: emp.experienceYears,
      experienceMonths: emp.experienceMonths,
      currentCompanyName: emp.currentCompanyName,
      previousCompanyName: emp.previousCompanyName,
      currentDesignation: emp.currentDesignation,
      previousDesignation: emp.previousDesignation,
      jobProfile: emp.jobProfile,
      department: emp.department,
      currentSalary: emp.currentSalary
    
  })
  setEmploymentBit(true) 
}


/*Upload Image of hr*/
export const uploadImage = (file) => {
  const formData = new FormData();
  formData.append("file", file); // 'image' should match the name expected by your server
  return axios.post(BASE_URL + "/applicant/upload-image",formData,{
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  }).then((response) => {
    return response.data;
  });
};

/*Update Image*/
export const upDateImage = (file) => {
  const formData = new FormData();
  formData.append("file", file); // 'image' should match the name expected by your server
  return axios.put(BASE_URL + "/applicant/update-image",formData,{
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  }).then((response) => {
    return response.data;
  });
};

/*remove image*/
export const removeImage=()=>{
  return axios.delete(BASE_URL + "/applicant/remove-image").then((response)=>{
    return response.data;
  })
}

export const updateProfile=(sendData)=>{
  return axios.post(BASE_URL+"/hr",sendData).then((response)=>{
    return response.data;
  })
}





