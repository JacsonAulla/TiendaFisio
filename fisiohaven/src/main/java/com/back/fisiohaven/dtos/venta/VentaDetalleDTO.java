package com.back.fisiohaven.dtos.venta;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaDetalleDTO {
    private VentaResumenDTO cabecera;
    private List<ItemVentaDTO> items;
}