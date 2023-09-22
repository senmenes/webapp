import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import axios from "axios";

function App() {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [rePassword, setRePassword] = useState();
  const [userName, setUserName] = useState();

  const onSubmit = (event) => {
    event.preventDefault();
    axios.post("/api/v1/createUser", {
      userName,
      password,
      email,
    });
  };

  return (
    <form onSubmit={onSubmit}>
      <div>
        <label htmlFor="userName">User Name</label>
        <input
          id="userName"
          onChange={(event) => setUserName(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="email">Email</label>
        <input id="email" onChange={(event) => setEmail(event.target.value)} />
      </div>
      <div>
        <label htmlFor="password">Password</label>
        <input
          id="password"
          type="password"
          onChange={(event) => setPassword(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="rePassword">Re-Password</label>
        <input
          id="rePassword"
          type="password"
          onChange={(event) => setRePassword(event.target.value)}
        />
      </div>
      <button
        disabled={!password || password !== rePassword}
        onClick={onSubmit}
      >
        Sign Up
      </button>
    </form>
  );
}

export default App;
