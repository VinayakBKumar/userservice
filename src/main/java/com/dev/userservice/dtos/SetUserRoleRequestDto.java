package com.dev.userservice.dtos;

import com.dev.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SetUserRoleRequestDto {
    Set<Long> roleIds;
}
