package com.example.dao;

import java.util.List;

import com.example.entities.User;
import com.example.models.ForgetPasswordRequest;
import com.example.models.LoginRequest;

public interface UserDao {
	boolean addUser(final User user);

	List<User> getAllUsers();

	void deleteById(final Long id);

	boolean userLogin(final LoginRequest loginRequest);

	boolean forgetPassword(final ForgetPasswordRequest forgetPasswordRequest);
}
