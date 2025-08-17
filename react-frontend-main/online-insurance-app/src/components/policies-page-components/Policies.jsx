import React, { useState } from "react";
import PolicyCard from "./PolicyCard/PolicyCard";

export default function Policies({ catalogData, cartItems, setCartItems }) {
  const [activeFilter, setActiveFilter] = useState("ALL");
  const sel = localStorage.getItem("userId");
  console.log(sel);
  const filterOptions = [
    "ALL",
    "LIFE",
    "MEDICAL",
    "VEHICLE",
    "FIRE",
    "PROPERTY",
  ];

  const filteredPolicies =
    activeFilter === "ALL"
      ? catalogData
      : catalogData.filter((policy) => policy.insuranceType === activeFilter);

  const PolicyCards = filteredPolicies.map((policyItem) => {
    return (
      <PolicyCard
        key={policyItem.policyId}
        userId={sel}
        policyId={policyItem.policyId}
        title={policyItem.policyName}
        description={policyItem.description}
        imageUrl="https://i.pravatar.cc"
        premium={policyItem.premium}
        insuranceType={policyItem.insuranceType}
        companyName={policyItem.companyName}
        cartItems={cartItems}
        setCartItems={setCartItems}
      />
    );
  });

  return (
    <div className="container px-5 py-12 mx-auto pb-32">
      <div className="flex justify-center space-x-4 mb-8">
        {filterOptions.map((option) => (
          <button
            key={option}
            className={`px-4 py-2 rounded-full focus:outline-none ${
              activeFilter === option
                ? "bg-blue-500 text-white"
                : "bg-gray-200 text-gray-700"
            }`}
            onClick={() => setActiveFilter(option)}
          >
            {option === "ALL" ? "All" : option}
          </button>
        ))}
      </div>
      <div className="flex flex-wrap items-center justify-center gap-20">
        {PolicyCards}
      </div>
    </div>
  );
}
