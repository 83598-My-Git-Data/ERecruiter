import axios, { toFormData } from "axios";
import { logout } from "./helper";
import { toast } from "react-toastify";

import { BASE_URL } from "./helper";

//base url for applicant contoller

const baseurl = BASE_URL+"/";

export function FetchAvalableJobs(jobs,setJobs){
    axios.get(baseurl+"job/available-job").then((resp)=>{
        setJobs(resp.data)
       
    }).catch((err)=>{console.log(err);})
}

export function SaveJob(jobId,savedJobs,setSavedJobs){
    axios.post(baseurl+"job/save-job/"+jobId).then((resp=>{
        if(resp){
            toast.success("Job saved")
            FetchSavedJobs(savedJobs,setSavedJobs)
        }
    })).catch((err)=>{
        if(err){
            toast.error("Job not saved")
        }
    })
}



export function UnSaveJob(jobId,savedJobs,setSavedJobs){
    axios.post(baseurl+"job/unsave-job/"+jobId).then((resp=>{
        if(resp){
            toast.warn("Job unsaved")
            FetchSavedJobs(savedJobs,setSavedJobs)
        }
    })).catch((err)=>{
        if(err){
            toast.error("Job not unsaved")
        }
    })
}

export function FetchSavedJobs(savedJobs,setSavedJobs){
    axios.get(baseurl+"job/get-saved-job").then((resp)=>{
        setSavedJobs(resp.data)
        
    }).catch((err)=>{console.log(err);})
}


export function FetchAppliedJobs(appliedJobs,setAppliedJobs){
    axios.get(baseurl+"job/get-applied-job").then((resp)=>{
        setAppliedJobs(resp.data)
       
    }).catch((err)=>{console.log(err);})
}

export function FetchShortlistedJobs(shortListedJobs,setShortListedJobs){
    axios.get(baseurl+"job/get-shortlisted-job").then((resp)=>{
        setShortListedJobs(resp.data)
       
    }).catch((err)=>{console.log(err);})
}

export function ApplyJob(jobId){
    axios.post(baseurl+"job/apply-job/"+jobId).then((resp=>{
        if(resp){
            toast.success("Applied Succesfully")
        }
    })).catch((err)=>{
        if(err){
            toast.error("Job could not be applied")
        }
    })
}

export function UnApplyJob(jobId){
    axios.delete(baseurl+"job/unapply-job/"+jobId).then((resp=>{
        if(resp){
            toast.success("Unapplied the job Succesfully")
        }
    })).catch((err)=>{
        if(err){
            toast.error("Job could not be unapplied")
        }
    })
}
