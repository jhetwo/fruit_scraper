package model;

import java.math.BigDecimal;
import java.security.cert.PKIXRevocationChecker;
import java.util.List;
import java.util.Optional;

public class FruitResults {
    private final List<RipeFruit> results;
    private final BigDecimal total;

    public FruitResults(List<RipeFruit> results, BigDecimal total) {
        this.results = results;
        this.total = total;
    }

    public Optional<BigDecimal> getTotal() {
        return Optional.ofNullable(total);
    }

    public Optional<List<RipeFruit>> getResults() {
        return Optional.of(results);
    }
}
