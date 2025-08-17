import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { selectOption } from "../redux/adminSlice";
import PolicyForm from "../components/PolicyForm";
import DiscountForm from "../components/DiscountForm";
import NavigationBar from "../components/NavigationBar";
import Footer from "../components/Footer";
import { Link } from "react-router-dom";

const AdminPage = () => {
  const dispatch = useDispatch();
  const selectedOption = useSelector((state) => state.admin.selectedOption);
  const [submissionMessage, setSubmissionMessage] = useState("");

  const handleOptionChange = (option) => {
    dispatch(selectOption(option));
  };

  const handleSubmit = async (data) => {
    try {
      setSubmissionMessage(data);
      setTimeout(() => {
        setSubmissionMessage("");
      }, 5000);
    } catch (error) {
      console.error("Error submitting form:", error);
      setSubmissionMessage("Submission failed. Please try again.");
    }
  };

  return (
    <>
      <NavigationBar />
      <div className="flex w-full justify-evenly">
        <div className="w-2/3 p-6 bg-white rounded-md shadow-md">
          <h1 className="text-3xl font-semibold mb-6 md:text-center">
            Admin Page
          </h1>
          <Link
            to="/feedbackList"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
          >
            See All Feedbacks
          </Link>
          <div className="md:flex md:justify-evenly">
            <div className="mb-4">
              <label className="inline-flex items-center">
                <input
                  type="radio"
                  value="policy"
                  checked={selectedOption === "policy"}
                  onChange={() => handleOptionChange("policy")}
                  className="form-radio text-blue-500"
                />
                <span className="ml-2">Add Policy</span>
              </label>
            </div>
            <div className="mb-4 ">
              <label className="inline-flex items-center">
                <input
                  type="radio"
                  value="discount"
                  checked={selectedOption === "discount"}
                  onChange={() => handleOptionChange("discount")}
                  className="form-radio text-blue-500"
                />
                <span className="ml-2">Add Discount</span>
              </label>
            </div>
          </div>
          <div>
            {selectedOption === "policy" && (
              <PolicyForm handleSubmit={handleSubmit} />
            )}
            {selectedOption === "discount" && (
              <DiscountForm handleSubmit={handleSubmit} />
            )}
            {submissionMessage && (
              <p className="text-green-500 mt-4">{submissionMessage}</p>
            )}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default AdminPage;
