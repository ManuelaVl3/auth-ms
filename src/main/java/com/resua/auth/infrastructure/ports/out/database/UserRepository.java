package com.resua.auth.infrastructure.ports.out.database;

import com.resua.auth.infrastructure.adapters.in.response.SecurityQuestionResponseDTO;
import com.resua.auth.infrastructure.adapters.out.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u " +
            "WHERE u.email = :email AND u.password = :password")
    Optional<UserEntity> findUserByCredentials(@Param("email") String email, @Param("password") String password);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findUserByEmail(String email);

    @Query("SELECT NEW " +
            "com.resua.auth.infrastructure.adapters.in.response.SecurityQuestionResponseDTO(u.id, u.securityQuestion) " +
            "FROM UserEntity u " +
            "WHERE u.email = :email")
    Optional<SecurityQuestionResponseDTO> findSecurityQuestion(@Param("email") String email);
}
