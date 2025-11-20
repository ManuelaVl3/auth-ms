package com.resua.auth.infrastructure.adapters.in.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Pregunta de seguridad del usuario")
public class SecurityQuestionResponseDTO {

    @Schema(description = "Id del usuario", example = "1")
    private Long id;

    @Schema(description = "Pregunta de seguridad", example = "¿Cuál es tu comida favorita?")
    private String securityQuestion;

}
