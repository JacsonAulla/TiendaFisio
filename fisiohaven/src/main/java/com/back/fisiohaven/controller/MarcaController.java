package com.back.fisiohaven.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.back.fisiohaven.dtos.ApiResponseDTO;
import com.back.fisiohaven.dtos.marca.MarcaResponseDTO;
import com.back.fisiohaven.service.MarcaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<MarcaResponseDTO>>> listarTodas() {
        List<MarcaResponseDTO> marcas = marcaService.listarTodas();
        return ResponseEntity.ok(
                new ApiResponseDTO<>("Marcas obtenidas exitosamente", marcas));
    }
}