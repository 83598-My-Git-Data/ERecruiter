import Header from "./common/Header/Header";
import { Outlet } from "react-router-dom";
function Dashboard() {
  return (
    <>
      <Header />
      <Outlet />
    </>
  );
}

export default Dashboard;
