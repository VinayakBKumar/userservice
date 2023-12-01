package com.dev.userservice.services.notificationservices.notificationrequestdtos.implementations;

import com.dev.userservice.dtos.UserDto;
import com.dev.userservice.models.notificationservice.EmailEventName;
import com.dev.userservice.models.notificationservice.EmailTemplate;
import com.dev.userservice.repositories.notificationservice.EmailNotificationTemplateRepository;
import com.dev.userservice.services.notificationservices.notificationrequestdtos.EmailNotificationRequestDto;
import com.dev.userservice.services.notificationservices.notificationrequestdtos.services.EmailNotificationRequestDtoService;
import org.springframework.stereotype.Service;

@Service
public class CustomEmailNotificationRequestDtoService implements EmailNotificationRequestDtoService {

    private EmailNotificationTemplateRepository emailNotificationTemplateRepository;
    private UserDto user;
    private EmailEventName emailEventName;
//
//    public CustomEmailNotificationRequestDtoService(UserDto user, EmailEventName emailEventName, EmailNotificationTemplateRepository emailNotificationTemplateRepository){
//        this.user = user;
//        this.emailEventName = emailEventName;
//        this.emailNotificationTemplateRepository = emailNotificationTemplateRepository;
//    }
    public CustomEmailNotificationRequestDtoService(EmailNotificationTemplateRepository emailNotificationTemplateRepository){
        this.emailNotificationTemplateRepository = emailNotificationTemplateRepository;
    }

    public void setUserDto(UserDto user){
        this.user = user;
    }

    public void setEmailEventName(EmailEventName emailEventName){
        this.emailEventName = emailEventName;
    }

    @Override
    public EmailNotificationRequestDto loadEmailNotificationRequestDto() {
        EmailTemplate emailTemplate = emailNotificationTemplateRepository.findEmailTemplateByEmailEventName(emailEventName.name());

        String customEmailBody = emailTemplate.getBody().replace("{USER_NAME}", user.getName());
        emailTemplate.setBody(customEmailBody);

        EmailNotificationRequestDto emailNotificationRequestDto = new CustomEmailNotificationRequestDto(user, emailTemplate);
        return emailNotificationRequestDto;
    }
}
