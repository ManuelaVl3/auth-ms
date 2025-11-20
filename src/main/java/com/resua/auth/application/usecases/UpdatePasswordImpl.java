package com.resua.auth.application.usecases;

import com.resua.auth.infrastructure.adapters.in.request.SecretAnswerDTO;
import com.resua.auth.infrastructure.adapters.in.request.UpdatePasswordRequestDTO;
import com.resua.auth.infrastructure.adapters.out.database.UserAdapter;
import com.resua.auth.infrastructure.ports.in.UpdatePassword;
import com.resua.auth.infrastructure.ports.in.ValidateSecretAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePasswordImpl implements UpdatePassword {

    private final UserAdapter userAdapter;

    @Override
    public boolean updatePassword(UpdatePasswordRequestDTO updatePasswordRequestDTO) {
        return userAdapter.updatePassword(updatePasswordRequestDTO);
    }
}

