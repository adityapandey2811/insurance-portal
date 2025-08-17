import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { submitForm } from "../redux/adminSlice";
import axios from "axios";

const PolicyForm = (props) => {
  const dispatch = useDispatch();
  const bearerToken = useSelector((state) => state.auth.token);
  const [policyData, setPolicyData] = useState({
    policyName: "",
    companyName: "",
    tenure: 0,
    premium: 0,
    coverage: 0,
    insuranceType: "",
  });

  const handleChange = (e) => {
    setPolicyData({ ...policyData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8077/insurance/addPolicy",
        policyData,
        {
          headers: {
            Authorization: `Bearer ${bearerToken}`,
          },
        }
      );
      dispatch(submitForm({ type: "policy", data: response.data }));
      props.handleSubmit("Policy Added..!!!");
      setPolicyData({
        policyName: "",
        companyName: "",
        tenure: 0,
        premium: 0,
        coverage: 0,
        insuranceType: "",
      });
    } catch (error) {
      console.error("Error submitting policy form:", error);
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded-md shadow-md">
      <h2 className="text-2xl font-semibold mb-6">Policy Catalogue Form</h2>
      <form onSubmit={handleSubmit} className="text-left">
        <div className="mb-4">
          <label
            htmlFor="policyName"
            className="block text-sm font-medium text-gray-600"
          >
            Policy Name
          </label>
          <input
            type="text"
            id="policyName"
            name="policyName"
            value={policyData.policyName}
            placeholder="Policy Name"
            onChange={handleChange}
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="companyName"
            className="block text-sm font-medium text-gray-600"
          >
            Company Name
          </label>
          <input
            type="text"
            id="companyName"
            name="companyName"
            value={policyData.companyName}
            placeholder="Company Name"
            onChange={handleChange}
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="tenure"
            className="block text-sm font-medium text-gray-600"
          >
            Tenure
          </label>
          <input
            type="number"
            id="tenure"
            name="tenure"
            value={policyData.tenure}
            onChange={handleChange}
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="premium"
            className="block text-sm font-medium text-gray-600"
          >
            Premium
          </label>
          <input
            type="number"
            id="premium"
            name="premium"
            value={policyData.premium}
            onChange={handleChange}
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="coverage"
            className="block text-sm font-medium text-gray-600"
          >
            Coverage
          </label>
          <input
            type="number"
            id="coverage"
            name="coverage"
            value={policyData.coverage}
            onChange={handleChange}
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="insuranceType"
            className="block text-sm font-medium text-gray-600"
          >
            Insurance Type
          </label>
          <select
            id="insuranceType"
            name="insuranceType"
            value={policyData.insuranceType}
            onChange={handleChange}
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          >
            <option value="" disabled>
              Select Insurance Type
            </option>
            <option value="LIFE">Life Insurance</option>
            <option value="MEDICAL">Medical Insurance</option>
            <option value="VEHICLE">Vehicle Insurance</option>
            <option value="PROPERTY">Property Insurance</option>
            <option value="FIRE">Fire Insurance</option>
          </select>
        </div>

        <button
          type="submit"
          className="w-full bg-blue-500 text-white p-2 rounded-md hover:bg-blue-600 focus:outline-none mb-6"
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default PolicyForm;
