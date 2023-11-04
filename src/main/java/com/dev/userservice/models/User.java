package com.dev.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonDeserialize(as = User.class)
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_ROLES",
        joinColumns = { @JoinColumn(name = "USER_ID")},
        inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    @JsonIgnore
    private Set<Role> roles;
}
