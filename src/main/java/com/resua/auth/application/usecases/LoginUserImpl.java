package com.resua.auth.application.usecases;

import com.resua.auth.domain.models.User;
import com.resua.auth.infrastructure.adapters.in.request.LoginRequestDTO;
import com.resua.auth.infrastructure.adapters.out.database.UserAdapter;
import com.resua.auth.infrastructure.ports.in.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUserImpl implements LoginUser {

    private final UserAdapter userAdapter;

    @Override
    public Optional<User> login(LoginRequestDTO loginRequest) {
        return userAdapter.getUserByCredentials(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
    }
}

