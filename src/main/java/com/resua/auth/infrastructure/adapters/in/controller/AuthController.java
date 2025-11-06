package com.resua.auth.infrastructure.adapters.in.controller;

import com.resua.auth.infrastructure.adapters.in.request.AuthRequestDTO;
import com.resua.auth.infrastructure.adapters.in.request.LoginRequestDTO;
import com.resua.auth.infrastructure.adapters.in.request.RegistrationRequestDTO;
import com.resua.auth.infrastructure.adapters.in.response.GenericResponseDTO;
import com.resua.auth.infrastructure.adapters.in.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Tag(name = "Autenticación", description = "API para gestión de autenticación de usuarios")
public class AuthController {

    @Operation(
            summary = "Registrar nuevo usuario",
            description = "Crea un nuevo usuario en el sistema con la información proporcionada"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario registrado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @PostMapping("/user")
    public ResponseEntity<GenericResponseDTO> add(@RequestBody RegistrationRequestDTO user){
        GenericResponseDTO response = new GenericResponseDTO();

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Autenticación de usuario",
            description = "Auntentica a un usuario en el sistema con la información proporcionada"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario autenticado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
   @PostMapping("/login")
    public ResponseEntity<GenericResponseDTO> login(@RequestBody LoginRequestDTO user){
        GenericResponseDTO response = new GenericResponseDTO("El usuario está autenticado correctamente");

        return ResponseEntity.ok(response);
   }

    @Operation(
            summary = "Obtener información de un usuario",
            description = "Obtiene la información de un usuario en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Información de usuario obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
   @GetMapping("/user")
    public ResponseEntity<UserResponseDTO> getUserInformation(@RequestParam("id") Long userId){
        UserResponseDTO response = new UserResponseDTO("Juliana Vélez Betancourt", "juliana@gmail.com",
                "123456789", "Contador público", "Nombre de su primera mascota?", "Nicky");

        return ResponseEntity.ok(response);
   }

    @Operation(
            summary = "Editar información de un usuario",
            description = "Edita la información de un usuario en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Información de usuario editada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
   @PatchMapping("/user/{id}")
    public ResponseEntity<GenericResponseDTO> update(@PathVariable("id") Long id, @RequestBody AuthRequestDTO user){
        GenericResponseDTO response = new GenericResponseDTO("El usuario con id: " + id + " se ha editado correctamente");

        return ResponseEntity.ok(response);
   }

    @Operation(
            summary = "Obtener pregunta de seguridad",
            description = "Obtiene la pregunta de seguridad de un usuario para recuperación de contraseña"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pregunta de seguridad obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID de usuario inválido",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @GetMapping("/user/question")
    public ResponseEntity<GenericResponseDTO> getSecurityQuestion(@RequestParam("id") Long userId) {
        GenericResponseDTO response = new GenericResponseDTO("¿Cuál es el nombre de tu mascota favorita?");
        
        return ResponseEntity.ok(response);
    }

}
