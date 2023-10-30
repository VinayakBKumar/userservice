package com.dev.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_ROLES",
        joinColumns = { @JoinColumn(name = "USER_ID")},
        inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    private Set<Role> roles;
}
