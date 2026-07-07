package com.back.fisiohaven.dtos.venta;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaResumenDTO {
    private Long id;
    private String numeroBoleta;
    private String fechaEmision;
    private String usuario;
    private BigDecimal total;
    private String estado;
}