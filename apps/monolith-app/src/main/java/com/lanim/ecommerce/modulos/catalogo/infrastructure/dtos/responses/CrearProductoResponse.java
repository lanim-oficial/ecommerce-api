package com.lanim.ecommerce.modulos.catalogo.infrastructure.dtos.responses;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record CrearProductoResponse(
        UUID id,
        String nombre,
        BigDecimal amount,
        Currency currency,
        Long stock,
        String descripcion,
        boolean publicado
) {
}