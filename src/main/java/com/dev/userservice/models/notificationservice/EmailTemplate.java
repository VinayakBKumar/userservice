package com.dev.userservice.models.notificationservice;

import com.dev.userservice.models.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EmailTemplate extends BaseModel {
    String emailEventName;
    String subject;
    String body;
}
