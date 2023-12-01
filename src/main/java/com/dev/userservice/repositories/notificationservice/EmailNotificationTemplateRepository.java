package com.dev.userservice.repositories.notificationservice;

import com.dev.userservice.models.notificationservice.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailNotificationTemplateRepository extends JpaRepository<EmailTemplate, Long> {
    EmailTemplate findEmailTemplateByEmailEventName(String emailEventName);
}
