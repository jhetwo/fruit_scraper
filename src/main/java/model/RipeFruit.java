package model;

import java.math.BigDecimal;
import java.util.Optional;

public class RipeFruit {
    private final String title;
    private final Float size;
    private final BigDecimal unitPrice;
    private final String description;

    public RipeFruit(String title, Float size, BigDecimal unitPrice, String description) {
        this.title = title;
        this.unitPrice = unitPrice;
        this.description = description;
        this.size = size;
    }

    public Optional<BigDecimal> getPrice() {
        return Optional.ofNullable(unitPrice);
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<Float> getSize() {
        return Optional.ofNullable(size);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }
}
