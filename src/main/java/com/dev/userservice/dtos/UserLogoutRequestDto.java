package com.dev.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserLogoutRequestDto {
    private Long userid;
    private String token;
}
