package com.back.fisiohaven.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroRequestDTO {

    @NotBlank(message = "El username no puede estar vacío")
    @Size(max = 50, message = "El username no puede tener más de 50 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 255, message = "La contraseña no puede tener más de 255 caracteres")
    private String password;

}
