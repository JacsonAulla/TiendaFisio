package com.back.fisiohaven.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.back.fisiohaven.dtos.ApiResponseDTO;
import com.back.fisiohaven.dtos.categoria.CategoriaResponseDTO;
import com.back.fisiohaven.service.CategoriaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<CategoriaResponseDTO>>> listarTodas() {
        List<CategoriaResponseDTO> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(
                new ApiResponseDTO<>("Categorías obtenidas exitosamente", categorias));
    }
}