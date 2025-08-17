import { login as userLogin } from "../reducers/loginLogoutRedux";
export const login = (username, password) => {
  const apiUrl = "http://localhost:8077/authenticate/login";
  return async (dispatch) => {
    try {
      const response = await fetch(apiUrl, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();
      const token = data.token;
      console.log(token);

      if (!data.userLoginResponse) {
        console.error("Authentication failed");
        return false;
      }

      dispatch({ type: "LOGIN_SUCCESS", payload: { username, token } });
      dispatch(userLogin());
      localStorage.setItem("token", data.token);
      localStorage.setItem("userId", data.userId);
      localStorage.setItem("userName", JSON.stringify(username));
      console.info("Logged in");
      return true;
    } catch (error) {
      console.error("Error during authentication", error);
    }
  };
};
