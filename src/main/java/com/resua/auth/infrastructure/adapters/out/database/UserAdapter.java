package com.resua.auth.infrastructure.adapters.out.database;

import com.resua.auth.domain.models.User;
import com.resua.auth.infrastructure.adapters.in.request.SecretAnswerDTO;
import com.resua.auth.infrastructure.adapters.in.request.UpdatePasswordRequestDTO;
import com.resua.auth.infrastructure.adapters.in.response.SecurityQuestionResponseDTO;
import com.resua.auth.infrastructure.adapters.out.database.entities.UserEntity;
import com.resua.auth.infrastructure.adapters.out.database.mappers.UserMapper;
import com.resua.auth.infrastructure.ports.out.database.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getUserByCredentials(String email, String password) {
        return userRepository.findUserByCredentials(email, password).map(userMapper::toModel);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId).map(userMapper::toModel);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toModel);
    }

    public User createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.toModel(savedUserEntity);
    }

    public User updateUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity updatedUserEntity = userRepository.save(userEntity);
        return userMapper.toModel(updatedUserEntity);
    }

    public Optional<SecurityQuestionResponseDTO> getQuestion(String email) {
        return userRepository.findSecurityQuestion(email);
    }

    public boolean validateAnswer(SecretAnswerDTO secretAnswerDTO) {
        Long id = secretAnswerDTO.getId();
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

        if (optionalUserEntity.isEmpty()) {
            return false;
        }

        UserEntity userEntity = optionalUserEntity.get();
        String storedAnswer = userEntity.getSecretAnswer();

        String providedAnswer = secretAnswerDTO.getSecretAnswer();

        return storedAnswer.equals(providedAnswer);
    }

    public boolean updatePassword(UpdatePasswordRequestDTO updatePasswordRequestDTO) {
        Long id = updatePasswordRequestDTO.getId();
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

        if (optionalUserEntity.isEmpty()) {
            return false;
        }

        String hashedPassword = passwordEncoder.encode(updatePasswordRequestDTO.getNewPassword());

        UserEntity userEntity = optionalUserEntity.get();
        userEntity.setPassword(hashedPassword);

        userRepository.save(userEntity);
        return true;
    }
}
