package com.dev.userservice.dtos;

import com.dev.userservice.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    Long id;
    String role;

    public static RoleDto from(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setRole(role.getRole());
        roleDto.setId(roleDto.getId());

        return roleDto;
    }
}
