import { useState } from "react";
import axios, { AxiosError } from "axios";
import { singUp } from "./api";
import { Input } from "./component/Input";

function SignUp() {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [rePassword, setRePassword] = useState();
  const [userName, setUserName] = useState();
  const [apiProgress, setApiProgress] = useState(false);
  const [successMessage, setSuccessMessage] = useState();
  const [errorMessage, setErrorMessage] = useState();

  const onSubmit = (event) => {
    setSuccessMessage(false);
    setErrorMessage();
    event.preventDefault();
    singUp({ userName, password, email })
      .then((response) => {
        console.log("qqqq");
        setSuccessMessage(response.data.id);
      })
      .catch((axiosError) => {
        setErrorMessage(axiosError.response.data.message);
      })
      .finally(() => setApiProgress(false));
    setApiProgress(true);
  };

  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <form onSubmit={onSubmit} className="card">
          <div className="text-center card-header">
            <h1>Sign Up</h1>
          </div>
          <div className="card-body">
            <Input
              errorMessage={errorMessage}
              label="User Name"
              id="userName"
              onChange={(event) => setUserName(event.target.value)}
            ></Input>
            <Input
              errorMessage={errorMessage}
              label="Email"
              id="email"
              onChange={(event) => setEmail(event.target.value)}
            ></Input>
            <Input
              errorMessage={errorMessage}
              label="Password"
              id="password"
              onChange={(event) => setPassword(event.target.value)}
              pass="password"
            ></Input>
            <Input
              errorMessage={errorMessage}
              label="Re-Password"
              id="rePassword"
              onChange={(event) => setRePassword(event.target.value)}
              pass="password"
            ></Input>
            <div>
              {successMessage && (
                <div className="alert alert-success">Successful</div>
              )}
            </div>
            {errorMessage && (
              <div className="alert alert-danger">{errorMessage}</div>
            )}

            <div className="text-center">
              <button
                className="btn btn-primary"
                disabled={!password || password !== rePassword || apiProgress}
                onClick={onSubmit}
              >
                {apiProgress && (
                  <>
                    <span
                      className="spinner-border spinner-border-sm"
                      aria-hidden="true"
                    ></span>
                    <span className="visually-hidden" role="status">
                      Loading...
                    </span>
                  </>
                )}
                Sign Up
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default SignUp;