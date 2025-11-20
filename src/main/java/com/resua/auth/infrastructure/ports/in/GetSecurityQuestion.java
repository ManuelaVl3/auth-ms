package com.resua.auth.infrastructure.ports.in;

import com.resua.auth.infrastructure.adapters.in.response.SecurityQuestionResponseDTO;

import java.util.Optional;

public interface GetSecurityQuestion {

    Optional<SecurityQuestionResponseDTO> getQuestion(String email);
}

