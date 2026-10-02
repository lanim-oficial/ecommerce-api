package com.lanim.ecommerce.modulos.catalogo.application.dtos.commands;

import java.math.BigDecimal;
import java.util.Currency;

public record CrearProductoCommand(
        String nombre,
        BigDecimal amount,
        Currency currency,
        Long stock,
        String descripcion
) {
}