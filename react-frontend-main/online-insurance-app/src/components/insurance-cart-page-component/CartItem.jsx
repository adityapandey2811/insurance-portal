import axios from "axios";
import React from "react";

function CartItem({
  singleCartItemData,
  allCartItems,
  setCartItemData,
  setallCartItems,
}) {
  const bearerToken = localStorage.getItem("token");
  const deleteFromCartHandler = async () => {
    const response = await axios.delete(
      "http://localhost:8077/insurancecart/deletePolicyFromCart",
      {
        headers: {
          Authorization: `Bearer ${bearerToken}`,
        },
        data: {
          userId: localStorage.getItem("userId"),
          policyId: singleCartItemData.policyId,
        },
      }
    );
    if (response.data.isPolicyDeleteSuccessfully) {
      setallCartItems(
        allCartItems.filter(
          (item) => item.policyId !== singleCartItemData.policyId
        )
      );
      console.log("Policy deleted");
    }
  };
  return (
    <div className="w-full h-28 m-4 flex flex-row justify-evenly items-center text-gray-700 bg-white shadow-md rounded-xl bg-clip-border">
      <div className="w-1/4 h-full flex justify-center items-center">
      <ion-icon name="pricetags" style={{ fontSize: '24px' }}></ion-icon>
      </div>
      <div className="w-1/4 h-full flex flex-col justify-center items-center">
        <h1 className="text-xl">{singleCartItemData.policyName}</h1>
        <h3>{singleCartItemData.insuranceType}</h3>
      </div>
      <div className="w-1/4 h-full flex flex-col justify-center items-center">
        <h2 className="text-xl">${singleCartItemData.premium}</h2>
      </div>
      <div className="w-1/4 h-full flex justify-center items-center flex-col">
        <button
          id="remove-item-from-cart"
          className="select-none rounded-lg bg-red-600 py-3 px-6 text-center align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40"
          onClick={deleteFromCartHandler}
        >
          Remove
        </button>
      </div>
    </div>
  );
}

export default CartItem;
