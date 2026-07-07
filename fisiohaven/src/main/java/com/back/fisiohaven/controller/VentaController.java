package com.back.fisiohaven.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.fisiohaven.dtos.ApiResponseDTO;
import com.back.fisiohaven.dtos.venta.VentaDetalleDTO;
import com.back.fisiohaven.dtos.venta.VentaRequestDTO;
import com.back.fisiohaven.dtos.venta.VentaResumenDTO;
import com.back.fisiohaven.service.VentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<?>> realizarVenta(@RequestBody VentaRequestDTO request,
            Authentication authentication) {
        String username = authentication.getName();

        ventaService.procesarVenta(request, username);

        return ResponseEntity.ok(
                new ApiResponseDTO<>("Venta procesada y stock actualizado exitosamente", null));
    }

    @GetMapping("/mis-compras")
    public ResponseEntity<ApiResponseDTO<List<VentaResumenDTO>>> obtenerHistorial(Authentication authentication) {
        String username = authentication.getName();
        List<VentaResumenDTO> historial = ventaService.obtenerMisCompras(username);

        return ResponseEntity.ok(
                new ApiResponseDTO<>("Historial de compras obtenido exitosamente", historial));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<VentaDetalleDTO>> verDetalleBoleta(
            @PathVariable Long id,
            Authentication authentication) {
        String username = authentication.getName();
        VentaDetalleDTO detalle = ventaService.obtenerDetalleVenta(id, username);

        return ResponseEntity.ok(
                new ApiResponseDTO<>("Detalle de boleta obtenido", detalle));
    }
}