package model;

import java.math.BigDecimal;
import java.util.Optional;

public class RipeFruit {
    private final String title;
    private final FileSize size;
    private final BigDecimal unitPrice;
    private final String description;

    public RipeFruit(String title, Float fileSize, BigDecimal unitPrice, String description) {
        this.title = title;
        this.size = new FileSize(fileSize);
        this.unitPrice = unitPrice;
        this.description = description;
    }

    public Optional<BigDecimal> getUnitPrice() {
        return Optional.ofNullable(unitPrice);
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<FileSize> getSize() {
        return Optional.ofNullable(size);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }
}
