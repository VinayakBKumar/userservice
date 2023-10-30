package com.dev.userservice.controllers;

import com.dev.userservice.dtos.SetUserRoleRequestDto;
import com.dev.userservice.dtos.UserDto;
import com.dev.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public ArrayList<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDto> setRoles(@PathVariable("id") Long userid, @RequestBody SetUserRoleRequestDto setUserRoleRequestDto){
        return userService.setRoles(userid, setUserRoleRequestDto);
    }
}
