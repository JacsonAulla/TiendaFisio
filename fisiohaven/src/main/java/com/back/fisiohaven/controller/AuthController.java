package com.back.fisiohaven.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.back.fisiohaven.dtos.ApiResponseDTO;
import com.back.fisiohaven.dtos.auth.JwtResponseDTO;
import com.back.fisiohaven.dtos.auth.LoginRequestDTO;
import com.back.fisiohaven.dtos.auth.RegistroRequestDTO;
import com.back.fisiohaven.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<ApiResponseDTO<Void>> registrar(@Valid @RequestBody RegistroRequestDTO registroRequestDTO) {

        authService.registro(registroRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDTO<>("Usuario registrado exitosamente", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<JwtResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        JwtResponseDTO jwtDto = authService.login(loginRequestDTO);
        return ResponseEntity.ok(new ApiResponseDTO<>("Login exitoso", jwtDto));
    }

}
