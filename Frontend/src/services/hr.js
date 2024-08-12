import axios from "axios";
import { BASE_URL } from "./helper";

/*Get hr details*/
export const getHrDetails = () => {
  return axios.get(BASE_URL + "/hr").then((response) => {
    return response.data;
  });
};

/*Upload Image of hr*/
export const uploadImage = (file) => {
  const formData = new FormData();
  formData.append("file", file); // 'image' should match the name expected by your server
  return axios.post(BASE_URL + "/hr/upload-image",formData,{
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
  return axios.put(BASE_URL + "/hr/update-image",formData,{
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  }).then((response) => {
    return response.data;
  });
};

/*remove image*/
export const removeImage=()=>{
  return axios.delete(BASE_URL + "/hr/remove-image").then((response)=>{
    return response.data;
  })
}

/*Update Profile*/
export const updateProfile=(sendData)=>{
  return axios.post(BASE_URL+"/hr",sendData).then((response)=>{
    return response.data;
  })
}

/*Create Job*/
export const createJobAndSave=(job)=>{
  return axios.post(BASE_URL+"/hr/create-job",job).then((response)=>{
    return response.data;
  })
}

/*Fetch all jobs*/
export const getAllJobs=()=>{
  return axios.get(BASE_URL+"/hr/jobs").then((response)=>{
    return response.data;
  })
}

/*Update Job*/
export const updateJob=(jobId,job)=>{
  return axios.put(BASE_URL+"/hr/job/update-job/"+jobId,job).then((response)=>{
    return response.data;
  })
}

/*Deactivate job by id*/
export const deactivateJobById=(id)=>{
  return axios.put(BASE_URL+"/hr/job/deactivate-job/"+id).then((response)=>{
    return response.data;
  })
}

/*get the applicants for the particular job*/
export const getApplicantsByJobId=(id)=>{
  return axios.get(BASE_URL+"/hr/applicants/"+id).then((response)=>{
    return response.data;
  })
}

/*Update status of applicant*/
export const updateStatus=(updateStatusBody)=>{
  return axios.put(BASE_URL+"/hr/update-status",updateStatusBody).then((response)=>{
    return response.data;
  })
}