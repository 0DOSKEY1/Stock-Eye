package com.stockeye;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

import com.stockeye.controller.AuthController;
import com.stockeye.dto.LoginRequest;
import com.stockeye.dto.LoginResponse;
import com.stockeye.model.User;
import com.stockeye.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testAuthenticateUser_Success() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("username", "password");
        User mockUser = new User("john", "smith");
        when(userService.authenticateUser("username", "password")).thenReturn(mockUser);

        // Act
        ResponseEntity<?> responseEntity = authController.authenticateUser(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Login successful", ((LoginResponse) responseEntity.getBody()).getMessage());
    }

    @Test
    public void testAuthenticateUser_Failure() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("username", "password");
        when(userService.authenticateUser("username", "password")).thenThrow(AuthenticationException.class);

        // Act
        ResponseEntity<?> responseEntity = authController.authenticateUser(loginRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid username or password", ((LoginResponse) responseEntity.getBody()).getMessage());
    }
}
