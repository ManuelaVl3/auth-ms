package com.resua.auth.infrastructure.adapters.in.controller;

import com.resua.auth.domain.models.User;
import com.resua.auth.infrastructure.adapters.in.request.LoginRequestDTO;
import com.resua.auth.infrastructure.adapters.in.request.RegistrationRequestDTO;
import com.resua.auth.infrastructure.adapters.in.request.UpdateUserRequestDTO;
import com.resua.auth.infrastructure.adapters.in.response.GenericResponseDTO;
import com.resua.auth.infrastructure.adapters.in.response.LoginResponseDTO;
import com.resua.auth.infrastructure.adapters.in.response.UserResponseDTO;
import com.resua.auth.infrastructure.ports.in.CreateUser;
import com.resua.auth.infrastructure.ports.in.GetUserById;
import com.resua.auth.infrastructure.ports.in.LoginUser;
import com.resua.auth.infrastructure.ports.in.UpdateUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Tag(name = "Autenticación", description = "API para gestión de autenticación de usuarios")
public class AuthController {

    private final CreateUser createUser;
    private final LoginUser loginUser;
    private final GetUserById getUserById;
    private final UpdateUser updateUser;

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
    public ResponseEntity<User> add(@RequestBody RegistrationRequestDTO userDTO){
        User createdUser = createUser.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @Operation(
            summary = "Autenticación de usuario",
            description = "Autentica a un usuario en el sistema con email y contraseña"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario autenticado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciales inválidas",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDTO.class)
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
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest){
        try {
            LoginResponseDTO response = loginUser.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(
                    new LoginResponseDTO(
                                "Credenciales inválidas",
                                null,
                                null,
                                null,
                                false,
                                null
                        ));
        }
   }

    @Operation(
            summary = "Obtener información de un usuario",
            description = "Obtiene la información de un usuario en el sistema por su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Información de usuario obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID de usuario inválido",
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
        return getUserById.getUserById(userId)
                .map(user -> {
                    UserResponseDTO response = new UserResponseDTO(
                            user.getName(),
                            user.getLastName(),
                            user.getEmail(),
                            user.getSecurityQuestion(),
                            user.getSecretAnswer()
                    );
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
   }

    @Operation(
            summary = "Editar información de un usuario",
            description = "Actualiza la información de un usuario en el sistema. Solo se actualizan los campos proporcionados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Información de usuario actualizada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content
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
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") Long id, @RequestBody UpdateUserRequestDTO updateRequest){
        return updateUser.updateUser(id, updateRequest)
                .map(user -> {
                    UserResponseDTO response = new UserResponseDTO(
                            user.getName(),
                            user.getLastName(),
                            user.getEmail(),
                            user.getSecurityQuestion(),
                            user.getSecretAnswer()
                    );
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
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
