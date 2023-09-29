import { Link, Outlet } from "react-router-dom";
import NavBar from "./shared/components/NavBar";

function App() {
  return (
    <>
      <NavBar></NavBar>
      <div className="container mt-3">
        <Outlet></Outlet>
      </div>
    </>
  );
}

export default App;
