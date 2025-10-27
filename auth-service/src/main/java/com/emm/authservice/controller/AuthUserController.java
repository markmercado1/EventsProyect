package com.emm.authservice.controller;


import com.emm.authservice.dtos.AuthUserDto;
import com.emm.authservice.enums.Role;
import com.emm.authservice.models.AuthUser;
import com.emm.authservice.dtos.TokenDto;
import com.emm.authservice.security.JwtProvider;
import com.emm.authservice.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthUserController {
    @Autowired
    AuthUserService authUserService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto) {
        TokenDto tokenDto = authUserService.login(authUserDto);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }
    @GetMapping("/validate-role")
    public ResponseEntity<Boolean> validateRole(
            @RequestParam String token,
            @RequestParam String role) {
        try {
            if (!jwtProvider.validate(token))
                return ResponseEntity.ok(false);

            Set<Role> roles = jwtProvider.getRolesFromToken(token);
            return ResponseEntity.ok(roles.contains(Role.valueOf(role)));
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token) {
        TokenDto tokenDto = authUserService.validate(token);
        if (tokenDto == null)
            return ResponseEntity
                .badRequest()
                .body(new TokenDto("Token inválido o expirado"));
        return ResponseEntity.ok(tokenDto);
    }
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth service activo");
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody AuthUserDto authUserDto) {
        AuthUser authUser = authUserService.save(authUserDto);
        if (authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }
}
