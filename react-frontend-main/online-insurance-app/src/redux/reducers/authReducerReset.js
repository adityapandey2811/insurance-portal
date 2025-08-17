
import { FORGOT_PASSWORD_SUCCESS, FORGOT_PASSWORD_FAILURE } from '../actions/authActionsReset';

const initialState = {
  user: null,
  error: null,
};


const authReducerReset = (state = initialState, action) => {
  switch (action.type) {

    case FORGOT_PASSWORD_SUCCESS:
      return {
        ...state,
        error: null,
      };
    case FORGOT_PASSWORD_FAILURE:
      return {
        ...state,
        error: action.payload,
      };
    default:
      return state;
  }
};

export default authReducerReset;
