package com.dev.userservice.services.notificationservices.notificationrequestdtos.services;

import com.dev.userservice.services.notificationservices.notificationrequestdtos.EmailNotificationRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface EmailNotificationRequestDtoService {

    EmailNotificationRequestDto loadEmailNotificationRequestDto();
}
