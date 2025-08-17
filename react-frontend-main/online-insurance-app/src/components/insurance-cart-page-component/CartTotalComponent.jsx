import axios, { all } from "axios";
import React from "react";

function CartTotalComponent({
  allCartItems,
  setallCartItems,
  allCatalogData,
  discountTotal,
  setTotalDiscount,
}) {
  const bearerToken = localStorage.getItem("token");
  const deleteCartHandler = async () => {
    const response = await axios.delete(
      `http://localhost:8077/insurancecart/clearCart/${localStorage.getItem(
        "userId"
      )}`,
      {
        headers: {
          Authorization: `Bearer ${bearerToken}`,
        },
      }
    );
    if (response.status == 200) {
      setallCartItems([]);
      console.log("All Policy deleted");
    }
  };
  const checkoutHandler = async () => {
    const response = await axios.post(
      "http://localhost:8077/insurancecart/createOrder",
      {
        userId: localStorage.getItem("userId"),
        isPaymentDone: true,
        totalAmountPaid: total(),
      },
      {
        headers: {
          Authorization: `Bearer ${bearerToken}`,
        },
      }
    );
    if (response.status == 200) {
      alert("Policies Purchased Successfully");
      setallCartItems([]);
      setTotalDiscount(0);
    }
  };
  const cartDetailArray = [];
  for (const element of allCartItems) {
    for (const dat of allCatalogData) {
      if (dat.policyId === element.policyId) {
        cartDetailArray.push(dat);
      }
    }
  }

  const total = () => {
    const val = cartDetailArray.reduce((total, policy) => {
      return total + policy.premium;
    }, 0);
    return val - discountTotal;
  };
  return (
    <div className="container justify-center flex items-center text-center align-middle mb-20 flex-col mx-auto">
      <h2 className="text-3xl">Cart Total: ${total()}</h2>
      <div className="flex flex-row">
        <button
          className="select-none m-5 rounded-lg bg-red-900 py-3 px-6 text-center align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40"
          onClick={deleteCartHandler}
        >
          CLEAR CART
        </button>
        <button
          className="select-none m-5 rounded-lg bg-blue-600 py-3 px-6 text-center align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40"
          onClick={checkoutHandler}
        >
          CHECKOUT
        </button>
      </div>
    </div>
  );
}

export default CartTotalComponent;
