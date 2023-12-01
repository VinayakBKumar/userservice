package com.dev.userservice.controllers;

import com.dev.userservice.dtos.*;
import com.dev.userservice.exceptions.AlreadyExistException;
import com.dev.userservice.exceptions.NotFoundException;
import com.dev.userservice.models.notificationservice.EmailEventName;
import com.dev.userservice.services.AuthService;
import com.dev.userservice.services.notificationservices.notificationrequestdtos.implementations.CustomEmailNotificationRequestDtoService;
import com.dev.userservice.services.notificationservices.notificationservicechooserstrategies.EmailNotificationServiceChooserStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    EmailNotificationServiceChooserStrategy emailNotificationServiceChooserStrategy;
    CustomEmailNotificationRequestDtoService customEmailNotificationRequestDtoService;

//    com.dev.userservice.services.notificationservices.kafkanotificationservices.KafkaConsumer kafkaConsumer;

//    public AuthController(AuthService authService, EmailNotificationServiceChooserStrategy emailNotificationServiceChooserStrategy
//            , CustomEmailNotificationRequestDtoService customEmailNotificationRequestDtoService){
    public AuthController(AuthService authService, EmailNotificationServiceChooserStrategy emailNotificationServiceChooserStrategy
//                          KafkaConsumer kafkaConsumer
    ){
        this.authService = authService;
        this.emailNotificationServiceChooserStrategy = emailNotificationServiceChooserStrategy;
//        this.customEmailNotificationRequestDtoService = customEmailNotificationRequestDtoService;
//        this.kafkaConsumer = kafkaConsumer;
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserSignupRequestDto userSignupRequestDto) throws AlreadyExistException {
        ResponseEntity<UserDto> signupResponse = authService.signup(userSignupRequestDto);

        if(signupResponse.getStatusCode() == HttpStatus.OK){

            //Email Notification Service
//            customEmailNotificationRequestDtoService.setUserDto(signupResponse.getBody());
//            customEmailNotificationRequestDtoService.setEmailEventName(EmailEventName.USER_SIGN_UP);
//            emailNotificationServiceChooserStrategy.chooseEmailNotificationService()
//                    .Notify();

            emailNotificationServiceChooserStrategy
                    .chooseEmailNotificationService(signupResponse.getBody(), EmailEventName.USER_SIGN_UP)
                    .Notify();
        }
        return signupResponse;
    }

//    @GetMapping("/consume")
//    public String emailListener(){
//        return kafkaConsumer.emailListener();
//    }


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
