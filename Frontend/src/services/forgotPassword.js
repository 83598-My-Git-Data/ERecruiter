import { BASE_URL } from "./helper";
import axios from "axios";

//send otp to mail
export const sendOtpToEmail = (email) => {
  return axios
    .put(BASE_URL + "/users/send-otp?email=" + email)
    .then((response) => {
      return response.data;
    });
};

//verify otp
export const verifyOtpEmail = (email, otp) => {
  return axios
    .post(BASE_URL + "/users/verify-otp", { email, otp })
    .then((response) => {
      return response.data;
    });
};

//reset password
export const resetPassword = (email, otp, password) => {
  return axios
    .put(`${BASE_URL}/users/reset-password?email=${email}&otp=${otp}`, {password})
    .then((response) => {
        return response.data;
    });
};
