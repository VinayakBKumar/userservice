package com.dev.userservice.securities;

import com.dev.userservice.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@JsonDeserialize(as = CustomSpringGrantAuthority.class)
@NoArgsConstructor
public class CustomSpringGrantAuthority implements GrantedAuthority {

    Role role;
    public CustomSpringGrantAuthority(Role role){
        this.role = role;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return role.getRole();
    }
}
