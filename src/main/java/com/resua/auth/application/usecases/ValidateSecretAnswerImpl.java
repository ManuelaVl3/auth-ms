package com.resua.auth.application.usecases;

import com.resua.auth.infrastructure.adapters.in.request.SecretAnswerDTO;
import com.resua.auth.infrastructure.adapters.out.database.UserAdapter;
import com.resua.auth.infrastructure.ports.in.ValidateSecretAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateSecretAnswerImpl implements ValidateSecretAnswer {

    private final UserAdapter userAdapter;

    @Override
    public boolean validate(SecretAnswerDTO secretAnswerDTO) {
        return userAdapter.validateAnswer(secretAnswerDTO);
    }
}

