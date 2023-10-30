package com.dev.userservice.controllers;

import com.dev.userservice.dtos.RoleDto;
import com.dev.userservice.dtos.RoleRequestDto;
import com.dev.userservice.exceptions.AlreadyExistException;
import com.dev.userservice.models.Role;
import com.dev.userservice.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleRequestDto roleRequestDto) throws AlreadyExistException {
        return roleService.createRole(roleRequestDto);
    }
}
