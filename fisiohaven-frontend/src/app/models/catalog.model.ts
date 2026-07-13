export interface ProductoResponseDTO {
    id: number;
    codigoSku: string;
    nombre: string;
    precio: number;
    marca: string;
    categoria: string;
    stock: number;
    estadoStock: string;
    imagenUrl: string;
}

export interface CategoriaResponseDTO {
    id: number;
    nombre: string;
}

export interface MarcaResponseDTO {
    id: number;
    nombre: string;
}

export interface Page<T> {
    content: T[];
    totalElements: number;
    totalPages: number;
    size: number;
    number: number;
}
