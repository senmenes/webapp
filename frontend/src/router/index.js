import App from "../App.jsx";
import Activation from "../pages/activation/index.jsx";
import Home from "../pages/home/index.jsx";
import SignUp from "../pages/signUp/index.jsx";
import { createBrowserRouter } from "react-router-dom";
import UserList from "../pages/userList/index.jsx";
export default createBrowserRouter([
  {
    path: "/",
    Component: App,
    children: [
      {
        path: "/",
        index: true,
        Component: Home,
      },
      {
        path: "/signup",
        Component: SignUp,
      },
      {
        path: "/activation/:token",
        Component: Activation,
      },
      {
        path: "/users",
        Component: UserList,
      },
    ],
  },
]);
