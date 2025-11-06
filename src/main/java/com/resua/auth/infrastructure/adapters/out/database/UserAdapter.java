package com.resua.auth.infrastructure.adapters.out.database;

import com.resua.auth.domain.models.User;
import com.resua.auth.infrastructure.adapters.out.database.entities.UserEntity;
import com.resua.auth.infrastructure.adapters.out.database.mappers.UserMapper;
import com.resua.auth.infrastructure.ports.out.database.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<User> getUserByCredentials(String email, String password) {
        return userRepository.findUserByCredentials(email, password).map(userMapper::toModel);
    }

    public User createUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.toModel(savedUserEntity);
    }
}
