package com.dev.userservice.services.notificationservices.kafkanotificationservices;

import com.dev.userservice.services.notificationservices.EmailNotificationService;
import com.dev.userservice.services.notificationservices.notificationrequestdtos.EmailNotificationRequestDto;
import com.dev.userservice.services.notificationservices.notificationrequestdtos.services.EmailNotificationRequestDtoService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEmailNotificationService implements EmailNotificationService {
    private EmailNotificationRequestDtoService emailNotificationRequestDtoService;
    private KafkaTemplate<String, Object> kafkaTemplate;
    public KafkaEmailNotificationService(KafkaTemplate<String, Object> kafkaTemplate,
                                         EmailNotificationRequestDtoService emailNotificationRequestDtoService){
        this.emailNotificationRequestDtoService = emailNotificationRequestDtoService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void Notify() {
        EmailNotificationRequestDto emailNotificationRequestDto =
            emailNotificationRequestDtoService.loadEmailNotificationRequestDto();
            kafkaTemplate.send("quickstart-events", emailNotificationRequestDto);
    }
}
