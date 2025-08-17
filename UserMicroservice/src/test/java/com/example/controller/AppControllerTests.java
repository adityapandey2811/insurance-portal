package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.entities.User;
import com.example.repo.UserTableRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.models.*;
import com.example.enums.UserType;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class AppControllerTests {
        private MockMvc mockMvc;
        @InjectMocks
        private AppController appController;
        @Mock
        private UserService userService;
        @Mock
        private AuthenticationManager authenticationManager;
        @Mock
        private UserTableRepo repo;

        @BeforeEach
        public void beforeTest() {
                MockitoAnnotations.openMocks(this);
                mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
        }

        @Test
        public void loginTest() {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername("testuser");
                loginRequest.setPassword("password");

                Authentication authentication = Mockito.mock(Authentication.class);
                Mockito.when(authentication.isAuthenticated()).thenReturn(true);
                Mockito.when(authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken("testuser", "password")))
                        .thenReturn(authentication);

                User user = new User();
                user.setUserId(1L);
                Optional<User> optionalUser = Optional.of(user);
                Mockito.when(repo.findByUsername("testuser")).thenReturn(optionalUser);

                Mockito.when(userService.generateToken("testuser")).thenReturn("generatedToken");

                ResponseEntity<Object> response = appController.login(loginRequest);

                assertNotNull(response);
                assertEquals(200, response.getStatusCodeValue());

                LoginResponse loginResponse = (LoginResponse) response.getBody();
                assertNotNull(loginResponse);
                assertEquals(true, loginResponse.isUserLoginResponse());
                assertEquals(1L, loginResponse.getUserId());
                assertEquals("generatedToken", loginResponse.getToken());
        }

        @Test
        public void registrationTest() throws Exception {
                RegistrationRequest registrationRequest = RegistrationRequest.builder()
                                .userId(1L)
                                .username("abc")
                                .firstName("xyz")
                                .lastName("pqr")
                                .phoneNo(1234567890L)
                                .userType(UserType.USER)
                                .gender("m")
                                .nickName("nickName")
                                .password("pwd")
                                .build();

                when(userService.addUserToSystem(any())).thenReturn(true);

                mockMvc.perform(post("/authenticate/registration")
                                .contentType("application/json")
                                .content(new ObjectMapper().writeValueAsString(registrationRequest)))
                                .andExpect(status().isOk())
                                .andExpect(content().json("{\"userRegistrationResponse\":true}"));
        }

        @Test
        public void forgetPasswordTest() throws Exception {
                ForgetPasswordRequest forgetPasswordRequest = ForgetPasswordRequest.builder()
                                .username("adi")
                                .nickName("nickName")
                                .newPassword("pwd")
                                .build();

                when(userService.forgetPassword(forgetPasswordRequest)).thenReturn(true);

                mockMvc.perform(post("/authenticate/forgetPassword")
                                .contentType("application/json")
                                .content(new ObjectMapper().writeValueAsString(forgetPasswordRequest)))
                                .andExpect(status().isOk())
                                .andExpect(content().string("Password Reset Successfully"));
        }

        @Test
        public void testDeleteUser() {
                Long userId = 1L;
                ResponseEntity<Object> response = appController.deleteUser(userId);
                assertEquals(200, response.getStatusCodeValue());
        }
}
