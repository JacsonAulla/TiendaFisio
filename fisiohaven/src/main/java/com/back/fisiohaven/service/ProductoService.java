package com.back.fisiohaven.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.back.fisiohaven.dtos.Producto.ProductoResponseDTO;
import com.back.fisiohaven.models.Producto;
import com.back.fisiohaven.repository.ProductoRepository;
import com.back.fisiohaven.specs.ProductoSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    public Page<ProductoResponseDTO> listarTodos(String nombre, List<Long> categoriasId, List<Long> marcasId, int page,
            int size) {

        Pageable configuracionPagina = PageRequest.of(page, size);

        Specification<Producto> filtros = Specification
                .where(ProductoSpecification.conNombre(nombre))
                .and(ProductoSpecification.conCategorias(categoriasId))
                .and(ProductoSpecification.conMarcas(marcasId));

        Page<Producto> paginaDeProductos = productoRepository.findAll(filtros, configuracionPagina);

        return paginaDeProductos.map(this::convertirADto);
    }

    private ProductoResponseDTO convertirADto(Producto producto) {
        ProductoResponseDTO dto = modelMapper.map(producto, ProductoResponseDTO.class);

        dto.setMarca(producto.getMarca().getNombre());
        dto.setCategoria(producto.getCategoria().getNombre());

        if (producto.getStockActual() == 0) {
            dto.setEstadoStock("Agotado");
        } else if (producto.getStockActual() <= 5) {
            dto.setEstadoStock("Poco stock");
        } else {
            dto.setEstadoStock("Disponible");
        }

        return dto;
    }
}