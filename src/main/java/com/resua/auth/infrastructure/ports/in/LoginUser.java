package com.resua.auth.infrastructure.ports.in;

import com.resua.auth.domain.models.User;
import com.resua.auth.infrastructure.adapters.in.request.LoginRequestDTO;

import java.util.Optional;

public interface LoginUser {

    Optional<User> login(LoginRequestDTO loginRequest);
}

