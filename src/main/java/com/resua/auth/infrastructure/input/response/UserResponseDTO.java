package com.resua.auth.infrastructure.input.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para mostrar infromación de un usuario")
public class UserResponseDTO {

    @Schema(description = "Nombre", example = "Manuela Vélez Betancourt", required = true)
    private String name;

    @Schema(description = "Email", example = "manuela@gmail.com", required = true)
    private String email;

    @Schema(description = "Password", example = "123456789", required = true)
    private String password;

    @Schema(description = "Profession", example = "Bióloga", required = true)
    private String profession;

    @Schema(description = "question", example = "Nombre de su primera mascota?", required = true)
    private String question;

    @Schema(description = "answer", example = "Nicky", required = true)
    private String answer;
}
