package com.example.service;

import com.example.models.ForgetPasswordRequest;
import com.example.models.LoginRequest;
import com.example.security.JwtUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.entities.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public String generateToken(String username) {
		return jwtUtil.generateToken(username);
	}

	@Override
	public void validateToken(String token) {
		jwtUtil.validateToken(token);
	}

	@Override
	public boolean addUserToSystem(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.addUser(user);
	}

	@Override
	public boolean userLogin(LoginRequest loginRequest) {
		return userDao.userLogin(loginRequest);
	}

	@Override
	public boolean forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
		forgetPasswordRequest.setNewPassword(passwordEncoder.encode(forgetPasswordRequest.getNewPassword()));
		return userDao.forgetPassword(forgetPasswordRequest);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

}
