package com.back.fisiohaven.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.back.fisiohaven.dtos.ApiResponseDTO;
import com.back.fisiohaven.dtos.Producto.ProductoResponseDTO;
import com.back.fisiohaven.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<ProductoResponseDTO>>> listarTodos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) List<Long> categoriasId,
            @RequestParam(required = false) List<Long> marcasId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductoResponseDTO> productosPaginados = productoService.listarTodos(nombre, categoriasId, marcasId, page,
                size);

        return ResponseEntity.ok(
                new ApiResponseDTO<>("Lista de productos obtenida exitosamente", productosPaginados));
    }
}
