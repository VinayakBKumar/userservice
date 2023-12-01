package com.dev.userservice.services.notificationservices.notificationservicechooserstrategies;

import com.dev.userservice.dtos.UserDto;
import com.dev.userservice.models.notificationservice.EmailEventName;
import com.dev.userservice.services.notificationservices.EmailNotificationService;
import org.springframework.stereotype.Service;

@Service
public interface EmailNotificationServiceChooserStrategy {
    EmailNotificationService chooseEmailNotificationService(UserDto user, EmailEventName emailEventName);
}
