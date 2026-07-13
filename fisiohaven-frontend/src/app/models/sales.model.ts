export interface VentaResumenDTO {
    id: number;
    numeroBoleta: string;
    fechaEmision: string;
    paciente: string;
    total: number;
    estado: string;
}

export interface ItemVentaDTO {
    nombreProducto: string;
    cantidad: number;
    precioUnitario: number;
    subtotal: number;
}

export interface VentaDetalleDTO {
    cabecera: VentaResumenDTO;
    items: ItemVentaDTO[];
}

export interface DetalleVentaRequestDTO {
    idProducto: number;
    cantidad: number;
}

export interface VentaRequestDTO {
    items: DetalleVentaRequestDTO[];
}
