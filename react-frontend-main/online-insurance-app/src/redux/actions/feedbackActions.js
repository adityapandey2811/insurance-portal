import axios from "axios";

export const addFeedback = (feedback) => async (dispatch) => {
  const bearerToken = localStorage.getItem("token");
  try {
    const response = await axios.post(
      "http://localhost:8077/feedback/addFeedback",
      feedback,
      {
        headers: {
          Authorization: `Bearer ${bearerToken}`,
        },
      }
    );
    dispatch({ type: "ADD_FEEDBACK_SUCCESS", payload: response.data });
  } catch (error) {
    dispatch({ type: "ADD_FEEDBACK_FAILURE", payload: error.message });
  }
};

export const getFeedbacks = () => async (dispatch) => {
  const bearerToken = localStorage.getItem("token");
  try {
    const response = await axios.get(
      "http://localhost:8077/feedback/showfeedbacks",
      {
        headers: {
          Authorization: `Bearer ${bearerToken}`,
        },
      }
    );
    console.log(response.data);
    dispatch({ type: "GET_FEEDBACKS_SUCCESS", payload: response.data });
  } catch (error) {
    dispatch({ type: "GET_FEEDBACKS_FAILURE", payload: error.message });
  }
};
