import { useEffect, useState } from "react";
import axios, { AxiosError } from "axios";
import { singUp } from "./api";
import { Input } from "./component/Input";
import ProgressingSpin from "../../shared/components/ProgressingSpin";

function SignUp() {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [rePassword, setRePassword] = useState();
  const [userName, setUserName] = useState();
  const [apiProgress, setApiProgress] = useState(false);
  const [successMessage, setSuccessMessage] = useState();
  const [errorMessage, setErrorMessage] = useState({});

  useEffect(() => {
    setErrorMessage(function (lastError) {
      return { ...lastError, userName: undefined };
    });
  }, [userName]);

  useEffect(() => {
    setErrorMessage(function (lastError) {
      return { ...lastError, email: undefined };
    });
  }, [email]);

  useEffect(() => {
    setErrorMessage(function (lastError) {
      return { ...lastError, password: undefined };
    });
  }, [password]);

  const onSubmit = (event) => {
    setSuccessMessage(false);
    setErrorMessage({});
    event.preventDefault();
    singUp({ userName, password, email })
      .then((response) => {
        console.log("qqqq");
        setSuccessMessage(response.data.id);
      })
      .catch((axiosError) => {
        if (axiosError.response?.data) {
          setErrorMessage(axiosError.response.data.validationErrors);
        }
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
              errorMessage={errorMessage.userName && errorMessage.userName}
              label="User Name"
              id="userName"
              onChange={(event) => setUserName(event.target.value)}
            ></Input>
            <Input
              errorMessage={errorMessage.email && errorMessage.email}
              label="Email"
              id="email"
              onChange={(event) => setEmail(event.target.value)}
            ></Input>
            <Input
              errorMessage={errorMessage.password && errorMessage.password}
              label="Password"
              id="password"
              onChange={(event) => setPassword(event.target.value)}
              pass="password"
            ></Input>
            <Input
              errorMessage={
                (errorMessage.password && errorMessage.password) ||
                (password !== rePassword && "Password does not match!")
              }
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
            {errorMessage.general && (
              <div className="alert alert-danger">
                Failed: {errorMessage.general}
              </div>
            )}

            <div className="text-center">
              <button
                className="btn btn-primary"
                disabled={!password || password !== rePassword || apiProgress}
                onClick={onSubmit}
              >
                {apiProgress && (
                  <>
                    <ProgressingSpin sm></ProgressingSpin>
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
