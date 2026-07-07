package com.back.fisiohaven.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.back.fisiohaven.dtos.ApiResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // error del front (400)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDTO<Object>> manejarErrorDelCliente(IllegalArgumentException ex) {
        ApiResponseDTO<Object> respuesta = new ApiResponseDTO<>(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    // error del sistema (500)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponseDTO<Object>> manejarErrorDeEjecucion(RuntimeException ex) {
        ex.printStackTrace();
        ApiResponseDTO<Object> respuesta = new ApiResponseDTO<>("Error interno: " + ex.toString(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    // error universal (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> manejarFalloUniversal(Exception ex) {
        ApiResponseDTO<Object> respuesta = new ApiResponseDTO<>("Fallo crítico y desconocido en el sistema", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    // Error de credenciales incorrectas en el Login (401)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseDTO<Object>> manejarCredencialesInvalidas(BadCredentialsException ex) {
        ApiResponseDTO<Object> respuesta = new ApiResponseDTO<>("Usuario o contraseña incorrectos", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
    }
}
