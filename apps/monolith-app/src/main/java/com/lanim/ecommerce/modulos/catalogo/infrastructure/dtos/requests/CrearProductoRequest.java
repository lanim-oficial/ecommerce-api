package com.lanim.ecommerce.modulos.catalogo.infrastructure.dtos.requests;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Currency;

public record CrearProductoRequest(
        @NotBlank
        @Size(min = 5, max = 300)
        String nombre,
        @NotNull
        @Positive
        BigDecimal amount,
        @NotNull
        Currency currency,
        @NotNull
        @PositiveOrZero
        Long stock,
        @NotNull
        @Size(max = 2000)
        String descripcion
) {
}
