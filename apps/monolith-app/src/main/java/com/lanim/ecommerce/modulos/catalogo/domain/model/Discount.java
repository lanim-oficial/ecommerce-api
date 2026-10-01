package com.lanim.ecommerce.modulos.catalogo.domain.model;

import com.lanim.ecommerce.common.valueobjects.Money;

import java.math.BigDecimal;

public record Discount(BigDecimal value) {

    public Discount{
        if(value == null || value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(BigDecimal.ONE) > 0){
            throw new IllegalArgumentException("el valor debe ser decimal entre 0 y 1");
        }
    }

    public Money applyDiscount(Money amount) {
        BigDecimal discountDone = amount.amount().multiply(this.value());
        return new Money(amount.amount().subtract(discountDone), amount.currency());
    }
}