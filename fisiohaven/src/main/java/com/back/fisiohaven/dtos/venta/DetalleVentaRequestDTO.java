package com.back.fisiohaven.dtos.venta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleVentaRequestDTO {
    private Long idProducto;
    private Integer cantidad;
}