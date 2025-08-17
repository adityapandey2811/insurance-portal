import { createSlice } from "@reduxjs/toolkit";

export const loginLogout = createSlice({
  name: "loginLogout",
  initialState: {
    loggedIn: localStorage.getItem("isLoggedIn"),
  },
  reducers: {
    login: (state) => {
      state.loggedIn = true;
      localStorage.setItem("isLoggedIn", JSON.stringify("true"));
    },
    logout: (state) => {
      state.loggedIn = false;
      localStorage.removeItem("userId");
      localStorage.removeItem("isLoggedIn");
      localStorage.removeItem("token");
      localStorage.removeItem("userName");
    },
  },
});

export const { login, logout } = loginLogout.actions;
export default loginLogout.reducer;
