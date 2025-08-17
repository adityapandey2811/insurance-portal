package com.example.service;

import com.example.dao.UserDao;
import com.example.models.ForgetPasswordRequest;
import com.example.models.LoginRequest;
import com.example.entities.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTests {
    @InjectMocks
    private UserService userService = new UserServiceImpl();
    @Mock
    private UserDao userDao;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addUserToSystemTest() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userDao.addUser(any(User.class))).thenReturn(true);
        boolean result = userService.addUserToSystem(user);
        assertTrue(result);
        Mockito.verify(userDao).addUser(user);
    }

    @Test
    public void userLogin_SuccessTest() {
        LoginRequest loginRequest = new LoginRequest("adi", "pwd");
        when(userDao.userLogin(loginRequest)).thenReturn(true);
        boolean result = userService.userLogin(loginRequest);
        assertTrue(result);
    }

    @Test
    public void userLogin_FailedTest() {
        LoginRequest loginRequest = new LoginRequest("adi", "pwd");
        when(userDao.userLogin(loginRequest)).thenReturn(false);
        boolean result = userService.userLogin(loginRequest);
        assertFalse(result);
    }

    @Test
    public void forgetPasswordTest() {
        ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest();
        forgetPasswordRequest.setUsername("testuser");
        forgetPasswordRequest.setNewPassword("newPassword");
        Mockito.when(passwordEncoder.encode(forgetPasswordRequest.getNewPassword())).thenReturn("encodedNewPassword");
        Mockito.when(userDao.forgetPassword(any(ForgetPasswordRequest.class))).thenReturn(true);
        boolean result = userService.forgetPassword(forgetPasswordRequest);
        assertTrue(result);
        Mockito.verify(userDao).forgetPassword(forgetPasswordRequest);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = Collections.singletonList(new User());
        Mockito.when(userDao.getAllUsers()).thenReturn(userList);
        List<User> result = userService.getAllUsers();
        assertEquals(1, result.size());
    }

    @Test
    public void testDeleteById() {
        Long userId = 1L;
        userService.deleteById(userId);
        Mockito.verify(userDao).deleteById(userId);
    }
}

