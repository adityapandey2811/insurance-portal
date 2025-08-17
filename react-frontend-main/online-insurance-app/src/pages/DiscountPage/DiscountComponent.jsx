import axios from "axios";
import React, { useState, useEffect } from "react";

function DiscountComponent({ cataLog, setTotalDiscount }) {
  const [discounts, setDiscounts] = useState([]);
  const [appliedDiscount, setAppliedDiscount] = useState(0);
  const [selectedDiscount, setSelectedDiscount] = useState(null);
  const bearerToken = localStorage.getItem("token");

  useEffect(() => {
    const fetchDiscounts = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8077/discount/showdiscount",
          {
            headers: {
              Authorization: `Bearer ${bearerToken}`,
            },
          }
        );
        console.log(response.data);
        if (response.status === 200) {
          const data = response.data;
          setDiscounts(data);
        } else {
          console.error("Failed to fetch available discounts");
        }
      } catch (error) {
        console.error("Error fetching available discounts", error);
      }
    };

    fetchDiscounts();
  }, []);

  const applyDiscount = (discount) => {
    setAppliedDiscount(discount.value);
    setTotalDiscount(discount.value);
    setSelectedDiscount(discount.policyId);
  };

  const removeDiscount = () => {
    setAppliedDiscount(0);
    setTotalDiscount(0);
    setSelectedDiscount(null);
  };

  const temp = [];
  for (const element of cataLog) {
    for (const dis of discounts) {
      if (element.policyId === dis.policyId) {
        temp.push({
          id: element.policyId,
          policyName: element.policyName,
          value: dis.value,
        });
      }
    }
  }

  return (
    <div>
      <h2 className="bg-lime-300 text-lg font-bold">Available Discounts</h2>
      {temp.length > 0 ? (
        <ul>
          {temp.map((discount) => (
            <li key={discount.id}>
              Coupon Id = {discount.id}, Value = ${discount.value}{" "}
              <button
                className={`select-none m-5 rounded-lg bg-red-900 py-3 px-6 text-center align-middle font-sans text-xs font-bold uppercase text-white shadow-md ${
                  selectedDiscount === discount.id
                    ? "bg-gray-500 cursor-not-allowed"
                    : "shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40"
                }`}
                onClick={() => applyDiscount(discount)}
                disabled={selectedDiscount !== null}
              >
                Apply
              </button>
            </li>
          ))}
        </ul>
      ) : (
        <p>No discounts available</p>
      )}

      {appliedDiscount > 0 && (
        <div>
          <h3>Total Discount Applied</h3>
          <p>${appliedDiscount}</p>
          <button
            className="m-5 rounded-lg bg-red-900 py-3 px-6 text-center align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40"
            onClick={removeDiscount}
          >
            Remove
          </button>
        </div>
      )}
    </div>
  );
}

export default DiscountComponent;
