package com.dev.userservice.services;

import com.dev.userservice.dtos.*;
import com.dev.userservice.models.Role;
import com.dev.userservice.models.User;
import com.dev.userservice.repositories.RoleRepository;
import com.dev.userservice.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<UserDto> getUser(Long id){
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty())
            return ResponseEntity.notFound().build();

        return new ResponseEntity<>(UserDto.from(userOptional.get()), HttpStatus.OK);
    }

    public ArrayList<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (User user : users){
            userDtos.add(UserDto.from(user));
        }
        return userDtos;
    }

    public ResponseEntity<UserDto> setRoles(Long userid, SetUserRoleRequestDto setUserRoleRequestDto){
        Optional<User> userOptional = userRepository.findById(userid);

        if(userOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Set<Role> roles = roleRepository.findAllByIdIn(setUserRoleRequestDto.getRoleIds());

        User user = userOptional.get();
        user.setRoles(Set.copyOf(roles));

        userRepository.save(user);

        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }
}
