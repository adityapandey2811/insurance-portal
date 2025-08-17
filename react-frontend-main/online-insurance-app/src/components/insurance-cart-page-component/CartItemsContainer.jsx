import React from "react";
import CartItem from "./CartItem";

function CartItemsContainer({
  allCartItems,
  setallCartItems,
  allCatalogData,
  setAllCatalogData,
}) {
  const cartDetailArray = [];
  for (let i = 0; i < allCartItems.length; i++) {
    for (let j = 0; j < allCatalogData.length; j++) {
      if (allCatalogData[j].policyId === allCartItems[i].policyId) {
        cartDetailArray.push(allCatalogData[j]);
      }
    }
  }

  const CartItems = cartDetailArray.map((item) => {
    return (
      <CartItem
        key={item.policyId}
        singleCartItemData={item}
        allCartItems={allCartItems}
        setallCartItems={setallCartItems}
      />
    );
  });

  return (
    <div className="container px-5 py-12 mx-auto flex flex-row flex-wrap items-start justify-center">
      {CartItems}
    </div>
  );
}

export default CartItemsContainer;
