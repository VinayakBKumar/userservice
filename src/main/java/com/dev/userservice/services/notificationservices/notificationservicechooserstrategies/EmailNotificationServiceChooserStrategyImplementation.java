package com.dev.userservice.services.notificationservices.notificationservicechooserstrategies;

import com.dev.userservice.dtos.UserDto;
import com.dev.userservice.models.notificationservice.EmailEventName;
import com.dev.userservice.services.notificationservices.EmailNotificationService;
import com.dev.userservice.services.notificationservices.kafkanotificationservices.KafkaEmailNotificationService;
import com.dev.userservice.services.notificationservices.notificationrequestdtos.implementations.CustomEmailNotificationRequestDtoService;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceChooserStrategyImplementation implements EmailNotificationServiceChooserStrategy {

    CustomEmailNotificationRequestDtoService customEmailNotificationRequestDtoService;
    KafkaEmailNotificationService kafkaEmailNotificationService;

    EmailNotificationServiceChooserStrategyImplementation(KafkaEmailNotificationService kafkaEmailNotificationService, CustomEmailNotificationRequestDtoService customEmailNotificationRequestDtoService){
            this.kafkaEmailNotificationService = kafkaEmailNotificationService;
            this.customEmailNotificationRequestDtoService = customEmailNotificationRequestDtoService;
    }

    @Override
    public EmailNotificationService chooseEmailNotificationService(UserDto user, EmailEventName emailEventName) {
        customEmailNotificationRequestDtoService.setUserDto(user);
        customEmailNotificationRequestDtoService.setEmailEventName(emailEventName);

        if(1 == 1){
            return kafkaEmailNotificationService;
        }
//        else{
//            //mail via some other api service
//        }
//
            return null;
        }
    }
