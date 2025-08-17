import React, { useEffect, useState } from "react";
import NavigationBar from "../../components/NavigationBar";
import PoliciesBanner from "../../components/policies-page-components/PoliciesBanner";
import Policies from "../../components/policies-page-components/Policies";
import Footer from "../../components/Footer";
import { useSelector } from "react-redux";
import axios from "axios";
import {catalogDataTest} from "../../testData/catalogData"

function PoliciesPage(props) {
  const [cartItems, setCartItems] = useState([]);
  const [catalogData, setCatalogData] = useState(catalogDataTest);
  const bearerToken = useSelector((state) => state.auth.token);
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
        setCatalogData(response.data);
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
        setCartItems(temp);
      } catch (error) {
        console.error("Error fetching catalog data:", error.message);
      }
    };

    fetchCartItemData();
  }, []);

  return (
    <div className="bg-[#f1f7fe]">
      <NavigationBar cartItemCount={cartItems.length} />
      <div className="mt-8">
        <PoliciesBanner
          title={"Policy Catalog"}
          value={"Choose any policy you need"}
        />
        <Policies
          catalogData={catalogData}
          cartItems={cartItems}
          setCartItems={setCartItems}
        />
      </div>
      <Footer />
    </div>
  );
}

export default PoliciesPage;
