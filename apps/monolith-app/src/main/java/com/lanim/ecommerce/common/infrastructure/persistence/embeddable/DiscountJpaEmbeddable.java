package com.lanim.ecommerce.common.infrastructure.persistence.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;


@Embeddable
public class DiscountJpaEmbeddable {
    @Column(
            name = "descuento",
            precision = 3,
            scale = 2
    )
    private BigDecimal descuento;

    public DiscountJpaEmbeddable(BigDecimal descuento) {
        this.descuento = descuento;
    }
    public DiscountJpaEmbeddable() {

    }
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }
}
