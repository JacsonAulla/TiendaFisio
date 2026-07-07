package com.back.fisiohaven.specs;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.back.fisiohaven.models.Producto;

public class ProductoSpecification {

    private ProductoSpecification() {
    }

    public static Specification<Producto> conNombre(String nombre) {
        return (root, query, criteriaBuilder) -> {
            if (nombre == null || nombre.trim().isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nombreGenerico")),
                    "%" + nombre.toLowerCase() + "%");
        };
    }

    public static Specification<Producto> conCategorias(List<Long> categoriasId) {
        return (root, query, criteriaBuilder) -> {
            if (categoriasId == null || categoriasId.isEmpty()) {
                return null;
            }
            return root.join("categoria").get("id").in(categoriasId);
        };
    }

    public static Specification<Producto> conMarcas(List<Long> marcasId) {
        return (root, query, criteriaBuilder) -> {
            if (marcasId == null || marcasId.isEmpty()) {
                return null;
            }
            return root.join("marca").get("id").in(marcasId);
        };
    }
}