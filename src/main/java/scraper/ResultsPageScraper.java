package scraper;

import model.FruitResults;
import model.RipeFruit;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsPageScraper {

    private final Document document;

    public ResultsPageScraper() {
        try {
            document = Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html").get();
        } catch (IOException e) {
            // Recovery not possible, fall over!
            throw new RuntimeException(e);
        }
    }

    public FruitResults getScrapeResults() {
        List<RipeFruit> ripeFruits;

        // Marshall html into objects
        Elements products = document.select(".product");
        ripeFruits = products.stream()
                .map(this::getFruitFromElement).collect(Collectors.toList());


        // Aggregate results
        BigDecimal total = ripeFruits.stream()
                .filter(ripeFruit -> ripeFruit.getUnitPrice().isPresent())
                .map(ripeFruit -> ripeFruit.getUnitPrice().get())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new FruitResults(ripeFruits, total);
    }

    private RipeFruit getFruitFromElement(Element element) {
        String title = getTitle(element);
        BigDecimal unitPrice = getPrice(element);
        String href = getHref(element);

        ProductPageScraper productPageScraper = new ProductPageScraper(href);
        Float fileSize = productPageScraper.getSize();
        String description = productPageScraper.getDescription();

        return new RipeFruit(title, fileSize, unitPrice, description);
    }

    private String getTitle(Element element) {
        return StringEscapeUtils.unescapeHtml4(element.select(".productInfo>h3>a").text());
    }

    private String getHref(Element element) {
        return element.select(".productInfo>h3>a").attr("href");
    }

    private BigDecimal getPrice(Element element) {
        String text = element.select(".pricing .pricePerUnit").text();
        String cleanedString = text.replace("&pound", "").replace("/unit", "");

        return BigDecimal.valueOf(Float.parseFloat(cleanedString))
                // Rounding mode shouldn't matter as it should never be the case that rounding possibilities will
                // be equidistant as we're dealing with tiny errors.
                .round(new MathContext(2, RoundingMode.HALF_DOWN));
    }
}
