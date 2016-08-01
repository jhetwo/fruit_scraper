package model;

import java.math.BigDecimal;
import java.util.List;

public class FruitResults {
    private final List<RipeFruit> results;
    private final BigDecimal total;

    public FruitResults(List<RipeFruit> results, BigDecimal total) {
        this.results = results;
        this.total = total;
    }
}
