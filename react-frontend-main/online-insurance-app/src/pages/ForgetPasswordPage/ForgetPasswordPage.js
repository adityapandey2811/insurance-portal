import React, { useState } from "react";
import { connect } from "react-redux";
import { forgotPassword } from "../../redux/actions/authActionsReset";
import "./ForgetPasswordPage.css";
import { Link } from "react-router-dom";

const ForgotPasswordPage = ({ forgotPassword, error }) => {
  const [username, setUsername] = useState("");
  const [nickName, setNickName] = useState("");
  const [newPassword, setNewPassword] = useState("");

  const handleForgotPassword = () => {
    forgotPassword({
      username,
      nickName,
      newPassword,
    });
  };

  return (
    <div className="screen-4">
      <div className="header">Forgot Password</div>
      <div className="username">
        <label htmlFor="username">Username</label>
        <div className="sec-2">
          <ion-icon name="person-outline"></ion-icon>
          <input
            type="text"
            value={username}
            placeholder="Username"
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
      </div>
      <div className="nickname">
        <label htmlFor="nickname">Nickname</label>
        <div className="sec-2">
          <ion-icon name="person-outline"></ion-icon>
          <input
            type="text"
            value={nickName}
            placeholder="Nickname"
            onChange={(e) => setNickName(e.target.value)}
          />
        </div>
      </div>
      <div className="new-password">
        <label htmlFor="newPassword">New Password</label>
        <div className="sec-2">
          <ion-icon name="lock-closed-outline"></ion-icon>
          <input
            type="password"
            value={newPassword}
            placeholder="New Password"
            onChange={(e) => setNewPassword(e.target.value)}
          />
        </div>
      </div>
      <button
        className="forgot-password"
        type="button"
        onClick={handleForgotPassword}
      >
        Reset Password
      </button>
      <div className="footer-forgot forgot-password-container">
        <Link to="/login" className="signup">
          Login
        </Link>
        <Link to="/register" className="signup">
          Sign Up
        </Link>
      </div>
    </div>
  );
};

const mapStateToProps = (state) => ({
  error: state.auth.error,
});

const mapDispatchToProps = {
  forgotPassword,
};

export default connect(mapStateToProps, mapDispatchToProps)(ForgotPasswordPage);
