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
    private String name;
    private Set<Role> roles;
    private String phoneNumber;

    public static UserDto from(User user){
        UserDto userDto = new UserDto();

        userDto.email = user.getEmail();
        userDto.name = user.getName();
        userDto.roles = user.getRoles();

        return userDto;
    }
}
