package com.back.fisiohaven.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back.fisiohaven.models.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    long count();

    List<Venta> findByUsuarioIdOrderByFechaEmisionDesc(Long usuarioId);
}