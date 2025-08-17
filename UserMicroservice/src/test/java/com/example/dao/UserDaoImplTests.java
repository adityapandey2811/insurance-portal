package com.example.dao;

import com.example.dao.UserDao;
import com.example.dao.UserDaoImpl;
import com.example.models.ForgetPasswordRequest;
import com.example.models.LoginRequest;
import com.example.entities.User;
import com.example.repo.UserTableRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserDaoImplTests {
    @InjectMocks
    private UserDao userDao = new UserDaoImpl();
    @Mock
    private UserTableRepo userTableRepo;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addUserTest() {
        User user = new User();
        when(userTableRepo.save(user)).thenReturn(user);
        boolean result = userDao.addUser(user);
        assertTrue(result);
        verify(userTableRepo, times(1)).save(user);
    }

    @Test
    public void userLogin_SuccessTest() {
        LoginRequest loginRequest = new LoginRequest("adi", "pwd");
        User user = new User();
        when(userTableRepo.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()))
                .thenReturn(user);
        boolean result = userDao.userLogin(loginRequest);
        assertTrue(result);
    }

    @Test
    public void userLogin_FailedTest() {
        LoginRequest loginRequest = new LoginRequest("adi", "pwd");
        when(userTableRepo.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()))
                .thenReturn(null);
        boolean result = userDao.userLogin(loginRequest);
        assertFalse(result);
    }

    @Test
    public void forgetPassword_SuccessTest() {
        ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest("adi", "nickName", "pwd");
        User user = new User();
        when(userTableRepo.findByUsernameAndNickName(forgetPasswordRequest.getUsername(), forgetPasswordRequest.getNickName()))
                .thenReturn(user);
        boolean result = userDao.forgetPassword(forgetPasswordRequest);
        assertTrue(result);
        verify(userTableRepo, times(1)).save(user);
    }

    @Test
    public void forgetPassword_InvalidUserTest() {
        ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest("adi", "nickName", "pwd");
        when(userTableRepo.findByUsernameAndNickName(forgetPasswordRequest.getUsername(), forgetPasswordRequest.getNickName()))
                .thenReturn(null);
        boolean result = userDao.forgetPassword(forgetPasswordRequest);
        assertFalse(result);
        verify(userTableRepo, never()).save(any(User.class));
    }

    @Test
    public void forgetPassword_SamePasswordTest() {
        ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest("adi", "nickName", "pwd");
        User user = new User();
        user.setPassword("pwd");
        when(userTableRepo.findByUsernameAndNickName(forgetPasswordRequest.getUsername(), forgetPasswordRequest.getNickName()))
                .thenReturn(user);
        boolean result = userDao.forgetPassword(forgetPasswordRequest);
        assertFalse(result);
        verify(userTableRepo, never()).save(any(User.class));
    }

    @Test
    public void getAllUsersTest() {
        List<User> userList = Collections.singletonList(new User());
        Mockito.when(userTableRepo.findAll()).thenReturn(userList);
        List<User> result = userDao.getAllUsers();
        assertEquals(1, result.size());
    }

    @Test
    public void deleteByIdTest() {
        Mockito.when(userTableRepo.existsById(1L)).thenReturn(true);
        userDao.deleteById(1L);
        Mockito.verify(userTableRepo).deleteById(1L);
    }
}
