package com.example.service;

import java.util.List;

import com.example.entities.User;
import com.example.models.ForgetPasswordRequest;
import com.example.models.LoginRequest;

public interface UserService {
	boolean addUserToSystem(final User user);

	boolean userLogin(final LoginRequest loginRequest);

	boolean forgetPassword(final ForgetPasswordRequest forgetPasswordRequest);

	List<User> getAllUsers();

	void deleteById(final Long id);

	String generateToken(String username);

	void validateToken(String token);
}
