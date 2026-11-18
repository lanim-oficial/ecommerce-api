package com.lanim.ecommerce.modulos.catalogo.application.dtos.out;

import java.math.BigDecimal;
import java.util.UUID;

public record CrearProductoOutputDto(
        UUID id,
        String nombre,
        BigDecimal amount,
        String currency,
        Long stock,
        String descripcion,
        boolean publicado) {
}
