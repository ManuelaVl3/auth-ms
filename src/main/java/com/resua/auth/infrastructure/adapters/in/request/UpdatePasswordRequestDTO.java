package com.resua.auth.infrastructure.adapters.in.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para actualizar un usuario")
public class UpdatePasswordRequestDTO {

    @Schema(description = "Id del usuario", example = "1")
    private Long id;

    @Schema(description = "Password", example = "nuevaPassword123")
    private String newPassword;
}

