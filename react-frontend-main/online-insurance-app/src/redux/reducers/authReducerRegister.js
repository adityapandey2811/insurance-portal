
import { REGISTER_SUCCESS, REGISTER_FAILURE } from '../actions/authActionsRegister';

const initialState = {
  user: null,
  error: null,
};

const authReducerRegister = (state = initialState, action) => {
  switch (action.type) {
    case REGISTER_SUCCESS:
      return {
        ...state,
        user: action.payload,
        error: null,
      };
    case REGISTER_FAILURE:
      return {
        ...state,
        user: null,
        error: action.payload,
      };
    default:
      return state;
  }
};

export default authReducerRegister;
