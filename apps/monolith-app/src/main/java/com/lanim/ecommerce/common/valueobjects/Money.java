package com.lanim.ecommerce.common.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public record Money(BigDecimal amount, Currency currency){
    public Money {
        if (amount == null || currency == null) {
            throw new IllegalArgumentException("ningun valor puede ser nula");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("el valor no puede ser negativo");
        }
    }

    public Money multiply(Money money) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("La moneda debe ser la misma");
        }

        Currency currency = money.currency;

        BigDecimal firstValue = this.amount;
        BigDecimal secondValue = money.amount;

        BigDecimal result = firstValue.multiply(secondValue);

        return new Money(result, currency);
    }
}
