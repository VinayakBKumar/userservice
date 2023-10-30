package com.dev.userservice.services;

import com.dev.userservice.dtos.RoleDto;
import com.dev.userservice.dtos.RoleRequestDto;
import com.dev.userservice.exceptions.AlreadyExistException;
import com.dev.userservice.models.Role;
import com.dev.userservice.repositories.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<RoleDto> createRole(RoleRequestDto roleRequestDto) throws AlreadyExistException {
        Optional<Role> roleOptional = roleRepository.findRoleByRole(roleRequestDto.getRole());

        if(!roleOptional.isEmpty())
            throw new AlreadyExistException("Already exists");

        Role role = new Role();

        role.setRole(roleRequestDto.getRole());

        role = roleRepository.save(role);

        return new ResponseEntity<>(RoleDto.from(role), HttpStatus.OK);
    }
}
