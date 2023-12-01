package com.dev.userservice.services.notificationservices.notificationrequestdtos.implementations;

import com.dev.userservice.dtos.UserDto;
import com.dev.userservice.models.notificationservice.EmailTemplate;
import com.dev.userservice.services.notificationservices.notificationrequestdtos.EmailNotificationRequestDto;
import org.springframework.stereotype.Service;

@Service
public class CustomEmailNotificationRequestDto implements EmailNotificationRequestDto {

    private UserDto user;
    private EmailTemplate emailTemplate;

    public CustomEmailNotificationRequestDto(){}
    public CustomEmailNotificationRequestDto(UserDto user, EmailTemplate emailTemplate){
        this.user = user;
        this.emailTemplate = emailTemplate;
    }
    @Override
    public String getTo() {
        return user.getEmail();
    }

    @Override
    public String getSubject() {
        return emailTemplate.getSubject();
    }

    @Override
    public String getBody() {
        return emailTemplate.getBody();
    }
}
