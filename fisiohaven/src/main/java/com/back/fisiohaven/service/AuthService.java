package com.back.fisiohaven.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fisiohaven.dtos.auth.JwtResponseDTO;
import com.back.fisiohaven.dtos.auth.LoginRequestDTO;
import com.back.fisiohaven.dtos.auth.RegistroRequestDTO;
import com.back.fisiohaven.models.Usuario;
import com.back.fisiohaven.repository.UsuarioRepository;
import com.back.fisiohaven.security.JwtService;
import com.back.fisiohaven.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void registro(RegistroRequestDTO registroRequestDTO) {

        if (usuarioRepository.existsByUsername(registroRequestDTO.getUsername())) {
            throw new IllegalArgumentException("El usuario ya Existe");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(registroRequestDTO.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(registroRequestDTO.getPassword()));
        nuevoUsuario.setRol("ROLE_USUARIO");

        usuarioRepository.save(nuevoUsuario);
    }

    @Transactional
    public JwtResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

        Usuario usuario = usuarioRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserDetailsImpl userDetails = new UserDetailsImpl(usuario);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("rol", usuario.getRol());

        String token = jwtService.generarToken(extraClaims, userDetails);

        return new JwtResponseDTO(token, usuario.getUsername(), usuario.getRol());
    }

}
