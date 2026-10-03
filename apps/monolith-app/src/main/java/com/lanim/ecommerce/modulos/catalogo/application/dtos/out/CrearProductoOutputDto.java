package com.lanim.ecommerce.modulos.catalogo.application.dtos.out;

import com.lanim.ecommerce.modulos.catalogo.application.dtos.commands.CrearProductoCommand;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record CrearProductoOutputDto(
        UUID id,
        String nombre,
        BigDecimal amount,
        Currency currency,
        Long stock,
        String descripcion,
        boolean publicado) {
}
