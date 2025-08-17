import axios from "axios";
import React, { useState } from "react";
import { FaShoppingCart } from "react-icons/fa";
import { useSelector } from "react-redux";
import { catalogDataTest } from "../../../testData/catalogData";

export default function PolicyCard({
  userId,
  policyId,
  title,
  description,
  imageUrl,
  premium,
  companyName,
  insuranceType,
  cartItems,
  setCartItems,
}) {
  const bearerToken = useSelector((state) => state.auth.token);
  const [isHovered, setIsHovered] = useState(false);
  const addToCartHandler = async () => {
    try {
      if (cartItems.some((item) => item.policyId === policyId)) {
        const response = await axios.delete(
          "http://localhost:8077/insurancecart/deletePolicyFromCart",
          {
            headers: {
              Authorization: `Bearer ${bearerToken}`,
            },
            data: { userId, policyId },
          }
        );
        console.log(response);
        if (response.data.isPolicyDeleteSuccessfully) {
          setCartItems(cartItems.filter((item) => item.policyId !== policyId));
          console.log("Policy deleted");
        }
      } else {
        const response = await axios.post(
          "http://localhost:8077/insurancecart/addPolicyToCart",
          { userId, policyId },
          {
            headers: {
              Authorization: `Bearer ${bearerToken}`,
            },
          }
        );
        console.log(response);
        if (response.data.isPolicyAddedSuccessfully) {
          setCartItems([...cartItems, { userId, policyId }]);
          console.log("Policy added");
        }
      }
    } catch (error) {
      console.error("Error adding to or deleting from cart", error);
    }
  };

  return (

    <div class="transition-transform transform hover:scale-110 relative flex w-full max-w-[20rem] flex-col rounded-xl bg-gradient-to-tr from-blue-600 to-pink-400 bg-clip-border p-8 text-white shadow-md shadow-pink-500/40">
      <div class="relative pb-8 m-0 mb-8 overflow-hidden text-center text-gray-700 bg-transparent border-b rounded-none shadow-none border-white/10 bg-clip-border">
        <p class="block font-sans text-sm antialiased font-normal leading-normal text-white uppercase">
          {title}
        </p>
        <h1 class="flex justify-center gap-1 mt-6 font-sans antialiased font-normal tracking-normal text-white text-7xl">
          <span class="mt-2 text-4xl">$</span>{premium}
          <span class="self-end text-4xl">/mo</span>
        </h1>
      </div>
      <div class="p-0">
        <ul class="flex flex-col gap-4">
          <li class="flex items-center gap-4">
            <span class="p-1 border rounded-full border-white/20 bg-white/20">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="2"
                stroke="currentColor"
                aria-hidden="true"
                class="w-3 h-3"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M4.5 12.75l6 6 9-13.5"
                ></path>
              </svg>
            </span>
            <p class="block font-sans text-base antialiased font-normal leading-relaxed text-inherit">
              {companyName}
            </p>
          </li>
          <li class="flex items-center gap-4">
            <span class="p-1 border rounded-full border-white/20 bg-white/20">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="2"
                stroke="currentColor"
                aria-hidden="true"
                class="w-3 h-3"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M4.5 12.75l6 6 9-13.5"
                ></path>
              </svg>
            </span>
            <p class="block font-sans text-base antialiased font-normal leading-relaxed text-inherit">
              {insuranceType}
            </p>
          </li>
        </ul>
      </div>
      <div class="p-0 mt-12">
        <button
          className="add-to-cart-button block w-full select-none rounded-lg bg-white py-3.5 px-7 text-center align-middle font-sans text-sm font-bold uppercase text-pink-500 shadow-md shadow-blue-gray-500/10 transition-all hover:scale-[1.02] hover:shadow-lg hover:shadow-blue-gray-500/20 focus:scale-[1.02] focus:opacity-[0.85] focus:shadow-none active:scale-100 active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
          type="button"
          onMouseEnter={() => setIsHovered(true)}
          onMouseLeave={() => setIsHovered(false)}
          onClick={addToCartHandler}
        >
          {isHovered ? (
            <span className="icon-content justify-center flex">
              <FaShoppingCart/>
            </span>
          ) : (
            <span className="text-content">
              {cartItems.some((item) => item.policyId === policyId)
                ? "Remove From Cart"
                : "Add to Cart"}
            </span>
          )}
        </button>
      </div>
    </div>
  );
}
