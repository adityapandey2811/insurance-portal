import React, { useEffect, useState } from "react";
import OrdersTable from "./OrdersTable";
import { orderData } from "../../testData/orderDataItems";
import axios from "axios";
export default function OrdersContainer() {
  const [allOrderDetails, setAllOrderDetails] = useState(orderData);
  const bearerToken = localStorage.getItem("token");
  useEffect(() => {
    const fetchCatalogData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8077/insurancecart/getAllOrderDetails/${localStorage.getItem(
            "userId"
          )}`,
          {
            headers: {
              Authorization: `Bearer ${bearerToken}`,
            },
          }
        );
        console.log(response);
        setAllOrderDetails(response.data);
      } catch (error) {
        console.error("Error fetching catalog data:", error.message);
      }
    };

    fetchCatalogData();
  }, []);

  const TABLE_HEADER = [
    "Order Id",
    "Policy List",
    "Total Amount Paid",
    "Payment Status",
    "Provide Feedback",
  ];
  const TABLE_ROWS = allOrderDetails;

  return (
    <div className="container mx-auto p-4">
      {allOrderDetails.length > 0 ? (
        <OrdersTable tableHeadings={TABLE_HEADER} tableRows={TABLE_ROWS} />
      ) : (
        <>You Have Not Purchased Any Policy Yet &#128054;</>
      )}
    </div>
  );
}
