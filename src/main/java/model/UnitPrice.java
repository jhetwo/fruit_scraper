package model;

import java.math.BigDecimal;

public class UnitPrice {
    private final BigDecimal price;

    public UnitPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
