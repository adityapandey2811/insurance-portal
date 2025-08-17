import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./HomePage/HomePage";
import CartPage from "./CartPage";
import LoginPage from "./LoginPage/LoginPage";
import RegistrationPage from "./RegistrationPage/RegistrationPage";
import AdminPage from "./AdminPage";
import PoliciesPage from "./AllPoliciesPage/PoliciesPage";
import AboutUs from "./AboutUs/AboutUs";
import ForgotPasswordPage from "./ForgetPasswordPage/ForgetPasswordPage";
import FeedbackFormPage from "./FeedbackPage/FeedbackForm"
import FeedbackListPage from "./FeedbackPage/FeedbackList"
import OrdersPage from "./OrdersPage/OrdersPage";

function Routers(props) {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} exact={true} />
        <Route path="/policies" element={<PoliciesPage />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegistrationPage />} />
        <Route path="/admin" element={<AdminPage />} />
        <Route path="/about" element={<AboutUs />} />
        <Route path="/forgetPass" element={<ForgotPasswordPage />} />
        <Route path="/feedback/:id" element={<FeedbackFormPage />} />
        <Route path="/feedbackList" element={<FeedbackListPage />} />
        <Route path="/orders" element={<OrdersPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Routers;
