import React from "react";

const PoliciesBanner = ({ title, value }) => {
  return (
    <div className="border-b border-gray-300 py-8">
      <h1 className="text-6xl font-semibold mb-2">{title}</h1>
      {/* <p className="text-sm text-gray-600">{value}</p> */}
    </div>
  );
};

export default PoliciesBanner;
