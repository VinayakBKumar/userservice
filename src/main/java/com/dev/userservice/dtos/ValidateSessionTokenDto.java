package com.dev.userservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateSessionTokenDto {
    Long userId;
    String token;
}
