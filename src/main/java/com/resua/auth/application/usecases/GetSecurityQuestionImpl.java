package com.resua.auth.application.usecases;

import com.resua.auth.infrastructure.adapters.in.response.SecurityQuestionResponseDTO;
import com.resua.auth.infrastructure.adapters.out.database.UserAdapter;
import com.resua.auth.infrastructure.ports.in.GetSecurityQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetSecurityQuestionImpl implements GetSecurityQuestion {

    private final UserAdapter userAdapter;

    @Override
    public Optional<SecurityQuestionResponseDTO> getQuestion(String email) {
        return userAdapter.getQuestion(email);
    }
}

