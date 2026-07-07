package com.back.fisiohaven.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fisiohaven.dtos.venta.DetalleVentaRequestDTO;
import com.back.fisiohaven.dtos.venta.ItemVentaDTO;
import com.back.fisiohaven.dtos.venta.VentaDetalleDTO;
import com.back.fisiohaven.dtos.venta.VentaRequestDTO;
import com.back.fisiohaven.dtos.venta.VentaResumenDTO;
import com.back.fisiohaven.models.DetalleVenta;
import com.back.fisiohaven.models.Producto;
import com.back.fisiohaven.models.Usuario;
import com.back.fisiohaven.models.Venta;
import com.back.fisiohaven.repository.ProductoRepository;
import com.back.fisiohaven.repository.UsuarioRepository;
import com.back.fisiohaven.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Venta procesarVenta(VentaRequestDTO request, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado en la base de datos"));

        Venta venta = new Venta();
        venta.setUsuario(usuario);
        venta.setFechaEmision(LocalDateTime.now(ZoneId.of("America/Lima")));
        venta.setEstado("Completado");
        venta.setNumeroBoleta(generarNumeroBoleta());

        BigDecimal totalVenta = BigDecimal.ZERO;
        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaRequestDTO item : request.getItems()) {

            Producto producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "El producto con ID " + item.getIdProducto() + " no existe"));

            if (producto.getStockActual() < item.getCantidad()) {
                throw new IllegalArgumentException(
                        "Stock insuficiente para el producto: " + producto.getNombreGenerico() + ". Stock actual: "
                                + producto.getStockActual());
            }

            producto.setStockActual(producto.getStockActual() - item.getCantidad());

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecioUnitario());

            BigDecimal subtotalLinea = producto.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad()));
            detalle.setSubtotal(subtotalLinea);

            detalles.add(detalle);
            totalVenta = totalVenta.add(subtotalLinea);
        }

        venta.setDetalles(detalles);
        venta.setTotal(totalVenta);
        venta.setSubtotal(totalVenta);
        venta.setIgv(BigDecimal.ZERO);

        return ventaRepository.save(venta);
    }

    private String generarNumeroBoleta() {
        long conteoActual = ventaRepository.count() + 1;
        int anioActual = LocalDateTime.now(ZoneId.of("America/Lima")).getYear();
        return String.format("INV-%d-%03d", anioActual, conteoActual);
    }

    @Transactional(readOnly = true)
    public List<VentaResumenDTO> obtenerMisCompras(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        List<Venta> ventas = ventaRepository.findByUsuarioIdOrderByFechaEmisionDesc(usuario.getId());

        return ventas.stream().map(venta -> {
            VentaResumenDTO dto = new VentaResumenDTO();
            dto.setId(venta.getId());
            dto.setNumeroBoleta(venta.getNumeroBoleta());
            dto.setFechaEmision(venta.getFechaEmision().toString());
            dto.setUsuario(venta.getUsuario().getUsername());
            dto.setTotal(venta.getTotal());
            dto.setEstado(venta.getEstado());
            return dto;
        }).toList();
    }

    @Transactional(readOnly = true)
    public VentaDetalleDTO obtenerDetalleVenta(Long idVenta, String username) {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new IllegalArgumentException("La boleta no existe"));

        if (!venta.getUsuario().getUsername().equals(username)) {
            throw new IllegalArgumentException("No tienes permiso para ver esta boleta");
        }

        VentaResumenDTO cabecera = new VentaResumenDTO();
        cabecera.setId(venta.getId());
        cabecera.setNumeroBoleta(venta.getNumeroBoleta());
        cabecera.setFechaEmision(venta.getFechaEmision().toString());
        cabecera.setUsuario(venta.getUsuario().getUsername());
        cabecera.setTotal(venta.getTotal());
        cabecera.setEstado(venta.getEstado());

        List<ItemVentaDTO> items = venta.getDetalles().stream().map(detalle -> {
            ItemVentaDTO itemDTO = new ItemVentaDTO();
            itemDTO.setNombreProducto(detalle.getProducto().getNombreGenerico());
            itemDTO.setCantidad(detalle.getCantidad());
            itemDTO.setPrecioUnitario(detalle.getPrecioUnitario());
            itemDTO.setSubtotal(detalle.getSubtotal());
            return itemDTO;
        }).toList();

        VentaDetalleDTO detalleCompleto = new VentaDetalleDTO();
        detalleCompleto.setCabecera(cabecera);
        detalleCompleto.setItems(items);

        return detalleCompleto;
    }
}