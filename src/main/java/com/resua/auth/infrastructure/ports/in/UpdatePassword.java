package com.resua.auth.infrastructure.ports.in;

import com.resua.auth.infrastructure.adapters.in.request.UpdatePasswordRequestDTO;

public interface UpdatePassword {

    boolean updatePassword(UpdatePasswordRequestDTO updatePasswordRequestDTO);
}

