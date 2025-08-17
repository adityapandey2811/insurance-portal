import React, { useEffect, useState } from "react";
import NavigationBar from "../components/NavigationBar";
import Footer from "../components/Footer";
import CartHeading from "../components/insurance-cart-page-component/CartHeading";
import { catalogData, catalogDataTest } from "../testData/catalogData";
import CartItemsContainer from "../components/insurance-cart-page-component/CartItemsContainer";
import CartTotalComponent from "../components/insurance-cart-page-component/CartTotalComponent";
import axios from "axios";
import { cartItemsDataTest } from "../testData/cartItemsData";
import DiscountComponent from "./DiscountPage/DiscountComponent";

function CartPage(props) {
  const [allCartItems, setallCartItems] = useState(cartItemsDataTest);
  const [allCatalogData, setAllCatalogData] = useState(catalogDataTest);
  const [totalDiscount, setTotalDiscount] = useState(0);

  const bearerToken = localStorage.getItem("token");

  useEffect(() => {
    const fetchCatalogData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8077/insurance/getAllPolicies",
          {
            headers: {
              Authorization: `Bearer ${bearerToken}`,
            },
          }
        );
        setAllCatalogData(response.data);
      } catch (error) {
        console.error("Error fetching catalog data:", error.message);
      }
    };

    fetchCatalogData();
  }, []);
  useEffect(() => {
    const fetchCartItemData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8077/insurancecart/getAllItemsFromCart/${localStorage.getItem(
            "userId"
          )}`,
          {
            headers: {
              Authorization: `Bearer ${bearerToken}`,
            },
          }
        );

        const temp = [];
        for (const element of response.data.listOfPolicyIds) {
          temp.push({
            userId: response.data.userId,
            policyId: element,
          });
        }
        setallCartItems(temp);
      } catch (error) {
        console.error("Error fetching catalog data:", error.message);
      }
    };

    fetchCartItemData();
  }, []);

  console.log(totalDiscount);
  return (
    <div className="bg-slate-50">
      <NavigationBar cartItemCount={allCartItems.length} />
      <div className="flex">
        <div className=" w-3/4 p-4">
          <CartHeading itemCount={allCartItems.length} />
          <CartItemsContainer
            allCartItems={allCartItems}
            setallCartItems={setallCartItems}
            allCatalogData={allCatalogData}
            setAllCatalogData={setAllCatalogData}
          />
          <CartTotalComponent
            allCartItems={allCartItems}
            setallCartItems={setallCartItems}
            allCatalogData={allCatalogData}
            discountTotal={totalDiscount}
            setTotalDiscount={setTotalDiscount}
          />
        </div>
        <div className="flex-shrink-0 w-1/4 p-4">
          <div className="bg-white rounded-md shadow-md p-4">
            <DiscountComponent
              cataLog={allCatalogData}
              setTotalDiscount={setTotalDiscount}
            />
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default CartPage;
