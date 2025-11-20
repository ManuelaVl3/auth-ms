package com.resua.auth.infrastructure.ports.in;

import com.resua.auth.infrastructure.adapters.in.request.SecretAnswerDTO;

public interface ValidateSecretAnswer {

    boolean validate(SecretAnswerDTO secretAnswerDTO);
}

