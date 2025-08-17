const initialState = {
  feedbacks: [],
  error: null,
};

const feedbackReducer = (state = initialState, action) => {
  console.log(action.payload);
  switch (action.type) {
    case "ADD_FEEDBACK_SUCCESS":
      return { ...state, error: null };
    case "ADD_FEEDBACK_FAILURE":
      return { ...state, error: action.payload };
    case "GET_FEEDBACKS_SUCCESS":
      return { ...state, feedbacks: action.payload };
    case "GET_FEEDBACKS_FAILURE":
      return { ...state, error: action.payload };
    default:
      return state;
  }
};

export default feedbackReducer;
