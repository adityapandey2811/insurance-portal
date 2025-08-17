import { createStore, applyMiddleware, combineReducers } from "redux";
import thunk from "redux-thunk";
import authReducer from "./reducers/authReducer";
import adminReducer from "./adminSlice";
import authReducerRegister from "./reducers/authReducerRegister";
import loginLogoutReducer from "./reducers/loginLogoutRedux";
import authReducerReset from "./reducers/authReducerReset";
import feedbackReducer from "./reducers/feedbackReducer";

const rootReducer = combineReducers({
  auth: authReducer,
  admin: adminReducer,
  authRegister: authReducerRegister,
  loginLogout: loginLogoutReducer,
  userReducer: authReducerReset,
  feedback: feedbackReducer,
});

const store = createStore(rootReducer, applyMiddleware(thunk));

export default store;
