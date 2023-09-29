import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import Alert from "../../shared/components/Alert";
import ProgressingSpin from "../../shared/components/ProgressingSpin";

function Activation() {
  const { token } = useParams();

  const [successMessage, setSuccessMessage] = useState();
  const [errorMessage, setErrorMessage] = useState();
  const [apiProgress, setApiProgress] = useState(true);

  useEffect(() => {
    axios
      .patch("/api/v1/activation/" + token)
      .then((response) => {
        console.log(response);
        setSuccessMessage("Account activated!");
        setErrorMessage();
      })
      .catch((axiosError) => {
        setSuccessMessage();
        setErrorMessage(axiosError.response.message);
        if (axiosError.response?.data) {
          setErrorMessage(axiosError.response.data);
        }
      })
      .finally(() => {
        setApiProgress(false);
      });
  }, []);

  return (
    <>
      {successMessage && <Alert styleType="success">{successMessage}</Alert>}
      {errorMessage && <Alert styleType="danger">{errorMessage}</Alert>}
      {apiProgress && <ProgressingSpin></ProgressingSpin>}
    </>
  );
}

export default Activation;
