import React from "react";
import PoliciesBanner from "../../components/policies-page-components/PoliciesBanner";
import NavigationBar from "../../components/NavigationBar";
import Footer from "../../components/Footer";
import OrdersContainer from "../../components/orders-page-component/OrdersContainer";

export default function OrdersPage() {
  return (
    <div>
      <NavigationBar />
      <div className="mt-8">
        <PoliciesBanner title={"Your Orders 🚀"} value={"See Your Orders."} />
        <OrdersContainer />
      </div>
      <Footer />
    </div>
  );
}
