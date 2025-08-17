import React from "react";

function CartHeading({ itemCount }) {
  return (
    <div className="m-3 p-3 mx-auto">
      <h1 className="text-2xl font-bold">Shopping Cart</h1>
      <p>You have {itemCount} items in the Shopping Cart.</p>
    </div>
  );
}

export default CartHeading;
