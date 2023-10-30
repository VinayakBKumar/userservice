package com.dev.userservice.controllers;

import com.dev.userservice.dtos.*;
import com.dev.userservice.exceptions.AlreadyExistException;
import com.dev.userservice.exceptions.NotFoundException;
import com.dev.userservice.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserSignupRequestDto userSignupRequestDto) throws AlreadyExistException {
        return authService.signup(userSignupRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) throws NotFoundException{
        return authService.login(userLoginRequestDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody UserLogoutRequestDto userLogoutRequestDto){
        return authService.logout(userLogoutRequestDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<JWTResponse> validateSession(@RequestBody ValidateSessionTokenDto validateSessionTokenDto){
        return authService.validateSession(validateSessionTokenDto);
    }
}
