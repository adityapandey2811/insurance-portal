import axios from "axios";

export const FORGOT_PASSWORD_SUCCESS = "FORGOT_PASSWORD_SUCCESS";
export const FORGOT_PASSWORD_FAILURE = "FORGOT_PASSWORD_FAILURE";

export const forgotPasswordSuccess = () => ({
  type: FORGOT_PASSWORD_SUCCESS,
});

export const forgotPasswordFailure = (error) => ({
  type: FORGOT_PASSWORD_FAILURE,
  payload: error,
});

export const forgotPassword = (userData) => async (dispatch) => {
  console.log(userData);
  try {
    const response = await axios.post(
      "http://localhost:8077/authenticate/forgetPassword",
      userData
    );
    console.log(response);
    dispatch(forgotPasswordSuccess());
  } catch (error) {
    dispatch(forgotPasswordFailure(error.message || "Forgot password failed."));
  }
};
