import axios from "axios";
import {BASE_URL} from './helper'
export const registerHR = (hrDetails) =>{
    return axios.post(BASE_URL+'/admin/register-hr',hrDetails).then((response)=>{
        return response.data;
    });
}

/*Return the hr list*/
/*
[
  {
    "id": 0,
    "firstName": "string",
    "lastName": "string",
    "gender": "MALE",
    "email": "string",
    "phoneNumber": "string",
    "qualification": "string",
    "status": true
  }
]
*/
export const getHrList =() =>{
    return axios.get(BASE_URL+'/admin/hr-list').then((response)=>{
      return response.data
    });
}

/*Returns the job list with specification*/
/*
[
  {
    "jobId": 0,
    "jobTitle": "string",
    "firstName": "string",
    "lastName": "string",
    "jobCreatedDate": "2024-02-16",
    "applicationDeadline": "2024-02-16",
    "location": "string",
    "vacancies": 0,
    "status": true
  }
]
*/
export const getJobList=()=>{
    return axios.get(BASE_URL+'/admin/job-list').then((response)=>{
        return response.data;
    })
}

//no of active,inactive and total (jobs and Hr)
export const getReport=()=>{
    return axios.get(BASE_URL+'/admin/analysis').then((response)=>{
        return response.data;
    })
}

// deactivate the hr account
export const deactivateHr = (id) => {
  return axios.put(BASE_URL + `/admin/deactivate-hr/${id}`)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      throw error; 
    });
};