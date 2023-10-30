package com.dev.userservice.dtos;

import com.dev.userservice.models.Role;
import com.dev.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    private Set<Role> roles;

    public static UserDto from(User user){
        UserDto userDto = new UserDto();

        userDto.email = user.getEmail();
        userDto.roles = user.getRoles();

        return userDto;
    }
}
