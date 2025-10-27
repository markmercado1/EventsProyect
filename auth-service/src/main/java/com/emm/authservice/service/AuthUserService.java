package com.emm.authservice.service;

import com.emm.authservice.dtos.AuthUserDto;
import com.emm.authservice.models.AuthUser;
import com.emm.authservice.dtos.TokenDto;

public interface AuthUserService {
    AuthUser save(AuthUserDto authUserDto);


    TokenDto login(AuthUserDto authUserDto);


    TokenDto validate(String token);


    AuthUserDto findById(int id);
}
