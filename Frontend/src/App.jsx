import AuthProvider from "./services/authprovider";
import Routes from "./routes/Router";

import "react-toastify/dist/ReactToastify.css";
function App() {
  return (
      <AuthProvider>
          <Routes />
        </AuthProvider>
  );
}

export default App;
