import React, { useState } from "react";
import { connect } from "react-redux";
import { login } from "../../redux/actions/authActions";
import { Link, useNavigate } from "react-router-dom";
import "./LoginPage.css";

const LoginPage = ({ login, error }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();
  const handleLogin = () => {
    if (login(username, password)) {
      console.log(username);
      if (username == "admin") navigate("/admin");
      else navigate("/");
    } else {
      alert("Login Failed");
      navigate("/login");
    }
  };

  return (
    <div className="screen-3">
      <div className="header">Login</div>
      <div className="username">
        <label for="username">Username</label>
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
      <div className="password">
        <label for="password">Password</label>
        <div className="sec-2">
          <ion-icon name="lock-closed-outline"></ion-icon>
          <input
            type={showPassword ? "text" : "password"}
            value={password}
            placeholder="Password"
            onChange={(e) => setPassword(e.target.value)}
          />
          <ion-icon
            className="show-hide"
            name="eye-outline"
            onClick={() => setShowPassword(!showPassword)}
          ></ion-icon>
        </div>
      </div>
      <button
        className="login login-container"
        type="button"
        onClick={handleLogin}
      >
        Login{" "}
      </button>
      <div className="footer-login">
        <Link to="/register">Signup</Link>
        <Link to="/forgetPass">Forgot Password?</Link>
      </div>
    </div>
  );
};

const mapStateToProps = (state) => ({
  error: state.error,
});

export default connect(mapStateToProps, { login })(LoginPage);
