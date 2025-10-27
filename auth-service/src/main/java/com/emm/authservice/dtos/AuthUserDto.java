package com.emm.authservice.dtos;


import com.emm.authservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {
    private String userName;
    private String password;
    private Set<Role> roles; // Agrega esto
}