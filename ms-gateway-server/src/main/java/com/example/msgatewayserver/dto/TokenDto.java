package com.example.msgatewayserver.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String token;
    private String userName;
    private Set<String> roles;
}


