package com.dev.userservice.services.notificationservices.notificationrequestdtos;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface EmailNotificationRequestDto extends Serializable {
    String getTo();

    String getSubject();

    String getBody();
}
