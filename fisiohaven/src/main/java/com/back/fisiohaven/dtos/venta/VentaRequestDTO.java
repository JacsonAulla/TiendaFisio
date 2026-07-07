package com.back.fisiohaven.dtos.venta;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaRequestDTO {
    private List<DetalleVentaRequestDTO> items;
}