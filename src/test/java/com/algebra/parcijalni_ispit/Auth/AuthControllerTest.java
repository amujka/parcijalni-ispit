package com.algebra.parcijalni_ispit.Auth;

import com.algebra.parcijalni_ispit.Auth.dto.LoginDto;
import com.algebra.parcijalni_ispit.security.JwtService;
import com.algebra.parcijalni_ispit.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;


class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    private LoginDto loginDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        loginDto = new LoginDto();
        loginDto.setUsername("user");
        loginDto.setPassword("password");
    }
}