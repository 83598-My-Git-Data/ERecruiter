import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import {
  sendOtpToEmail,
  verifyOtpEmail,
  resetPassword,
} from "../../services/forgotPassword";

export const ForgotPassword = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [isSent, setSentOtp] = useState(false);
  const [isOtpVerified, setOtpVerified] = useState(false);
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  //loading state to show user for sending email
  const [isLoading, setLoading] = useState(false);

  //handle otp send
  const handleSendOtp = () => {
    setLoading(true);
    sendOtpToEmail(email)
      .then((response) => {
        toast.success(`Otp sent to ${email},check your mail`);
        setSentOtp(true);
      })
      .catch((err) => {
        toast.error(`email not found!!`);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  //Otp verifying
  const verifyOtp = () => {
    verifyOtpEmail(email, otp)
      .then((response) => {
        console.log(response);
        if (response.email === email && response.otp === otp) {
          toast.success("verified successfully! change your password");
          setOtpVerified(true);
        }
      })
      .catch((err) => {
        toast.error("something bad happend");
      });
  };
  //check for password correctness
  const isValidPassword = (password) => {
    // Password validation using regex
    return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/.test(password);
  };
  //Reset password
  const resetPasswordAndRedirect = () => {
    if (password !== confirmPassword) {
      toast.error("password and confirm password doesn't match");
    } else if (!isValidPassword(password)) {
      toast.warn("enter valid password");
    } else {
      resetPassword(email, otp, password).then((response) => {
        toast.success("Password reset successfully!");
        navigate("/signin");
      });
    }
  };

  return (
    <div className="container" style={{ marginTop: 100 }}>
      <div
        className="row justify-content-center my-5"
        style={{ backgroundColor: "#f5f5f5" }}
      >
        <div id="sign-in-bar" className="text-center">
          <h2>{isOtpVerified ? <>Reset Password</> : <>Forgot Password</>}</h2>
        </div>
        <div className="col-6">
          <br></br>
          <b style={{ color: "#9B7ED9", fontSize: 22 }}>Email:</b>
          <input
            type="text"
            className="form-control mb-3"
            id="email"
            name="email"
            value={email}
            placeholder="safinplace@gmail.com"
            autoComplete="off"
            onChange={(e) => {
              setEmail(e.target.value);
            }}
            required
            disabled={isOtpVerified}
          />
          {/* If the otp sent successfully then 
          it will show the enter otp field
          */}
          {isSent ? (
            <>
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>Enter Otp:</b>
              <input
                type="number"
                className="form-control mb-2"
                id="otp"
                name="otp"
                value={otp}
                placeholder="XXXXXX"
                autoComplete="off"
                onChange={(e) => {
                  setOtp(e.target.value);
                }}
                required
                disabled={isOtpVerified}
              />
            </>
          ) : (
            <></>
          )}
          {isOtpVerified ? <></> : <></>}
          {isSent ? (
            <>
              <div className="buttons">
                <button
                  type="submit"
                  className="btn btn-primary mt-2 mb-2"
                  onClick={verifyOtp}
                  hidden={isOtpVerified}
                >
                  Verify Otp
                </button>
              </div>
            </>
          ) : (
            <>
              <div className="buttons">
                <button
                  type="submit"
                  className="btn btn-success mt-2 mb-2"
                  onClick={handleSendOtp}
                >
                  {isLoading?<>
                    <span
                    className="spinner-border spinner-border-sm"
                    role="status"
                    aria-hidden="true"
                  ></span>
                  </>:<></>}
                  <span className="m-2" style={{fontSize:24}}>Send otp</span>
                </button>
              </div>
            </>
          )}
          {isOtpVerified ? (
            <div>
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>Password:</b>
              <input
                type="text"
                className="form-control mb-3"
                id="password"
                name="password"
                value={password}
                autoComplete="off"
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
                required
              />
              <b style={{ color: "#9B7ED9", fontSize: 22 }}>
                Confirm password:
              </b>
              <input
                type="text"
                className="form-control mb-3"
                id="password"
                name="confirmPassword"
                value={confirmPassword}
                autoComplete="off"
                onChange={(e) => {
                  setConfirmPassword(e.target.value);
                }}
                required
              />
              <div className="buttons">
                <button
                  className="btn mt-2 mb-2"
                  onClick={resetPasswordAndRedirect}
                >
                  Reset Password
                </button>
              </div>
            </div>
          ) : (
            <></>
          )}
        </div>
      </div>
      <ToastContainer />
    </div>
  );
};

export default ForgotPassword;
