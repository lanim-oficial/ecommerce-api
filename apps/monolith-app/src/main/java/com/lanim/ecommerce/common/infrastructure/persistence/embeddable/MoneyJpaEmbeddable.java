package com.lanim.ecommerce.common.infrastructure.persistence.embeddable;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class MoneyJpaEmbeddable {
    @Column(
            name = "monto",
            nullable = false,
            columnDefinition =  "NUMERIC"
    )
    private BigDecimal amount;
    @Column(
            name = "divisa",
            nullable = false,
            length = 3
    )
    private String currencyCode;

    public MoneyJpaEmbeddable(BigDecimal amount, String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    protected MoneyJpaEmbeddable() {}

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
