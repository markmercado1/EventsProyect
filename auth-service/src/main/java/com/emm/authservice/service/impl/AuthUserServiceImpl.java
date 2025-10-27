package com.emm.authservice.service.impl;


import com.emm.authservice.dtos.AuthUserDto;
import com.emm.authservice.enums.Role;
import com.emm.authservice.models.AuthUser;
import com.emm.authservice.dtos.TokenDto;
import com.emm.authservice.repository.AuthUserRepository;
import com.emm.authservice.security.JwtProvider;
import com.emm.authservice.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;


    @Override
    public AuthUser save(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (user.isPresent())
            return null;

        String password = passwordEncoder.encode(authUserDto.getPassword());

        // Si no vienen roles, asigna uno por defecto
        Set<Role> roles = authUserDto.getRoles();
        if (roles == null || roles.isEmpty()) {
            roles = Set.of(Role.ROLE_USER); // rol por defecto
        }

        AuthUser authUser = AuthUser.builder()
                .userName(authUserDto.getUserName())
                .password(password)
                .roles(roles) // Agrega esto
                .build();

        return authUserRepository.save(authUser);
    }



    @Override
    public TokenDto login(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (!user.isPresent())
            return null;

        if (passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword())) {
            String token = jwtProvider.createToken(user.get());

            Set<String> roleNames = user.get().getRoles().stream()
                    .map(Role::name)
                    .collect(Collectors.toSet());

            return new TokenDto(token, user.get().getUserName(), roleNames);
        }

        return null;
    }




    @Override
    public TokenDto validate(String token) {
        if (!jwtProvider.validate(token))
            return null;

        String username = jwtProvider.getUserNameFromToken(token);
        Optional<AuthUser> user = authUserRepository.findByUserName(username);

        if (!user.isPresent())
            return null;

        // Extrae los roles del token
        Set<Role> roles = jwtProvider.getRolesFromToken(token);
        Set<String> roleNames = roles.stream()
                .map(Role::name)
                .collect(Collectors.toSet());

        return new TokenDto(token, username, roleNames);
    }
}
