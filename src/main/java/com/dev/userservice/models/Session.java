package com.dev.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends BaseModel{
    @ManyToOne
    private User user;
    private String token;
    private LocalDateTime expiryingAt;
    @Enumerated(value = EnumType.ORDINAL)
    private SessionStatus sessionStatus;
}



