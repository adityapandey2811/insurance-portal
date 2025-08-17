const initialState = {
  user: null,
  token: localStorage.getItem("token"),
  error: null,
  userId: localStorage.getItem("userId"),
};

const authReducer = (state = initialState, action) => {
  switch (action.type) {
    case "LOGIN_SUCCESS":
      return {
        ...state,
        user: action.payload.username,
        token: action.payload.token,
        userId: action.payload.userId,
        error: null,
      };
    case "LOGIN_FAILURE":
      return {
        ...state,
        user: null,
        token: null,
        error: "Authentication failed. Please check your credentials.",
      };
    default:
      return state;
  }
};

export default authReducer;
