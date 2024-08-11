import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../services/authprovider";

/*component will serve as a wrapper for our authenticated routes, 
ensuring that only authenticated users can access them*/
export const ProtectedRoute = () => {
  const { token } = useAuth();

  // Check if the user is authenticated
  if (!token) {
    // If not authenticated, redirect to the login page
    return <Navigate to="/signin"/>;
  }
  
  // If authenticated, render the child routes
  return <Outlet />;
};
