import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { addFeedback } from "../../redux/actions/feedbackActions";
import { useParams, useNavigate } from "react-router-dom";
import "./FeedbackForm.css";

const FeedbackForm = (props) => {
  const params = useParams();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [feedback, setFeedback] = useState({
    orderId: params.id,
    feedbackDescription: "",
    feedbackDate: Date.now(),
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(addFeedback(feedback));
    navigate("/orders");
    alert("Feedback Added");
  };

  return (
    <div className="screen-5">
      <div className="header">Feedback Form</div>
      <div className="orderid">
        <label htmlFor="orderid">Order ID</label>
        <div className="sec-2">
          <ion-icon name="person-outline"></ion-icon>
          <input
            type="text"
            value={params.id}
            disabled
            placeholder="Order ID"
            onChange={(e) =>
              setFeedback({ ...feedback, orderId: e.target.value })
            }
          />
        </div>
      </div>
      <div className="feedback-message">
        <label htmlFor="feedback-message">Feedback</label>
        <div className="sec-2">
          <ion-icon name="mail-outline"></ion-icon>
          <input
            type="text"
            value={feedback.feedbackDescription}
            placeholder="Feedback"
            onChange={(e) =>
              setFeedback({ ...feedback, feedbackDescription: e.target.value })
            }
          />
        </div>
      </div>
      <button className="submit-feedback" type="button" onClick={handleSubmit}>
        Submit
      </button>
    </div>
  );
};

export default FeedbackForm;
