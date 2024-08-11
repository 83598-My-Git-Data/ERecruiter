import {
  createRoutesFromElements,
  createBrowserRouter,
  Route,
  RouterProvider,
  Navigate,
} from "react-router-dom";
import AdminDashboard from "../components/Admin/AdminDashboard";
import { useAuth } from "../services/authprovider";
import { ProtectedRoute } from "./ProtectedRoute";
import Dashboard from "../components/Dashboard";
import ContactUs from "../components/common/ContactUs/ContatctUs";
import AboutUs from "../components/common/AboutUs/About";
import Home from "../components/common/Home/Home";
import Register from "../components/auth/Register";
import LogIn from "../components/auth/Login";
import { jwtDecode } from "jwt-decode";
import ApplicantWelcomePage from "../components/applicant/ApplicantWelcomePage/ApplicantWelcomePage";
import ProfilePage from "../components/applicant/ProfilePage/Applicantprofilepage";
import RegisterHr from "../components/Admin/RegisterHr";
import HrList from "../components/Admin/HrList";
import JobList from "../components/Admin/JobList";
import Report from "../components/Admin/Report";
import { ForgotPassword } from "../components/auth/ForgotPassword";
import Admin from "../assets/images/admin.jpg";
import { HrDashboard } from "../components/Hr/HrDashboard";
import { HrProfile } from "../components/Hr/HrProfile";
import { EditHrProfile } from "../components/Hr/EditProfile";
import { CreateJob } from "../components/Hr/CreateJob";
import ApplicantJobs from "../components/applicant/ApplicantJobs/ApplicantJobs"
import Applicantbg from "../assets/images/Applicantbg.jpg"
import AvailbleJobs from "../components/applicant/AvailableJobs"
import SavedJobs from "../components/applicant/SavedJobs";
import AppliedJobs from "../components/applicant/AppliedJobs";
import ShortListedJobs from "../components/applicant/ShortListedJobs";
import { HRJobList } from "../components/Hr/HrJobList";
import { UpdateJob } from "../components/Hr/UpdateJob";
import { Applicants } from "../components/Hr/Applicants";

const Routes = () => {
  /*
  useAuth hook is called to retrieve the token value from the authentication context
  */
  const { token } = useAuth();

  // Check if the user has specific roles based on the decoded token
  const userRoles = token ? jwtDecode(token).authorities : [];

  //All public routes that will be shown to all users
  const routesForPublic = createRoutesFromElements(
    <Route key="public" path="/" element={<Dashboard />}>
      <Route id="public1" path="/" element={<Home />}></Route>
      <Route id="public2" path="/home" element={<Home />}></Route>
      <Route id="public3" path="/contact-us" element={<ContactUs />}></Route>
      <Route id="public4" path="/about-us" element={<AboutUs />}></Route>
      <Route id="public5" path="/signup" element={<Register />}></Route>
      <Route id="public6" path="/signin" element={<LogIn />}></Route>
      {/* Routes for forgot pasword */}
      <Route
        id="public7"
        path="/forgot-password"
        element={<ForgotPassword />}
      ></Route>
    </Route>
  );

  //All routes that are for admin only
  const routesForAdminOnly = createRoutesFromElements(
    <Route id="admin1" path="/" element={<ProtectedRoute />}>
      <Route id="admin2" path="admin" element={<AdminDashboard />}>
        <Route id="admin3" path="register-hr" element={<RegisterHr />}></Route>
        <Route id="admin4" path="job-list" element={<JobList />}></Route>
        <Route id="admin5" path="hr-list" element={<HrList />}></Route>
        <Route id="admin6" path="report" element={<Report />}></Route>
        <Route
          id="admin7"
          path=""
          element={
            <>
              <div>
                <img src={Admin} className={"img-fluid"}></img>
              </div>
            </>
          }
        ></Route>
      </Route>
    </Route>
  );

  //All routes that are for Hr Only
  const routesForHROnly = createRoutesFromElements(
    <Route id="hr1" path="/" element={<ProtectedRoute />}>
      {/* For HR Prfole and dashboard */}
      <Route id="hr2" path="/hr" element={<HrDashboard/>}>
        <Route id="hrChild1" path="update-profile" element={<EditHrProfile/>}></Route>
        <Route id="hrChild2" path="jobs" element={<HRJobList/>}></Route>
        <Route id="hrChild3" path="create-job" element={<CreateJob/>}></Route>
        <Route id="hrChild4" path="applicants" element={<Applicants/>}></Route>
        <Route id="hrChild5" path="" element={<HrProfile/>}></Route>
        <Route id="hrChild6" path="update-job" element={<UpdateJob/>}></Route>
      </Route>
    </Route>
  );

  const routesForApplicant = createRoutesFromElements(
    <Route id="applicant" path="/" element={<ProtectedRoute />}>
      {/*Add your applicant component here element={<Applicant/>}*/}
      <Route id="applicant1" path="applicant" element={<ApplicantWelcomePage />}></Route>
      <Route id="applicant3" path="jobs" element={<ApplicantJobs />}>
      <Route id="applicant8" path="" element={<>
        
              <div>
                <img src={Applicantbg} className={"img-fluid"}></img>
              </div>
            </>}></Route>
        <Route id="applicant4" path="available-jobs" element={<AvailbleJobs/>}></Route>
        <Route id="applicant5" path="applied-jobs" element={<AppliedJobs/>}></Route>
        <Route id="applicant6" path="saved-jobs" element={<SavedJobs/>}></Route>
        <Route id="applicant7" path="shortlisted-jobs" element={<ShortListedJobs/>}></Route>
      </Route>
      <Route id="applicant2" path="profile" element={<ProfilePage />}></Route>
    </Route>
  );

  //In this route we will show that use is un authorized for
  // particular page
  const routeForNotAuthorizedHR = createRoutesFromElements(
    <Route id="hrUnauth" path="*" element={<ProtectedRoute />}>
      <Route
        id="hrUnauth1"
        path="*"
        element={
          <>
            <br />
            <br />
            <br />
            <br />
            <h1>You are not supposed to view this page</h1>
          </>
        }
      ></Route>
    </Route>
  );
  const routeForNotAuthorizedAdmin = createRoutesFromElements(
    <Route id="adminUnAuth" path="*" element={<ProtectedRoute />}>
      <Route
        id="adminUnAuth1"
        path="*"
        element={
          <>
            <br />
            <br />
            <br />
            <br />
            <h1>You are not supposed to view this page</h1>
          </>
        }
      ></Route>
    </Route>
  );

  //In this route we will show that use is un authorized for
  // particular page
  const routeForNotAuthorizedApplicant = createRoutesFromElements(
    <Route id="applicantUnAuth" path="*" element={<ProtectedRoute />}>
      <Route
        id="applicantUnAuth1"
        path="*"
        element={
          <>
            <br />
            <br />
            <br />
            <br />
            <h1>Unauthorized User</h1>
          </>
        }
      ></Route>
    </Route>
  );

  const router = createBrowserRouter([
    /*All public routes*/
    ...routesForPublic,
    /*IF the user is logged in as a HR else unauthorized*/
    ...(userRoles.includes("ROLE_HR")
      ? routesForHROnly
      : routeForNotAuthorizedHR),
    /*IF the user is logged in as a ADMIN else unauthorized*/
    ...(userRoles.includes("ROLE_ADMIN")
      ? routesForAdminOnly
      : routeForNotAuthorizedAdmin),
    /*IF the user is logged in as a Applicant else unauthorized*/
    ...(userRoles.includes("ROLE_APPLICANT")
      ? routesForApplicant
      : routeForNotAuthorizedApplicant),
    !token && <Navigate to="/signin"></Navigate>,
  ]);
  return <RouterProvider router={router} />;
};

export default Routes;
