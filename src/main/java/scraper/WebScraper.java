package scraper;

import feign.Feign;
import model.FruitResults;
import model.RipeFruit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import repository.RipeFruitHtmlRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class WebScraper {

    private final RipeFruitHtmlRepository ripeFruitHtmlRepository;

    public WebScraper() {
        ripeFruitHtmlRepository = Feign.builder()
                .target(RipeFruitHtmlRepository.class, "http://hiring-tests.s3-website-eu-west-1.amazonaws.com");
    }

    public FruitResults getScrapeResults() {
        List<RipeFruit> ripeFruits;

        // Get html string
        try {
            Document document = Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html").get();

            Elements products = document.select(".product");

            ripeFruits = products.stream()
                    .map(e -> getFruitFromElement(e)).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Marshall html into objects

        // Aggregate results
        // return results
        BigDecimal total = ripeFruits.stream()
                .map(ripeFruit -> ripeFruit.getPrice().get())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        FruitResults fruitResults = new FruitResults(ripeFruits, total);
        return fruitResults;
    }

    private RipeFruit getFruitFromElement(Element element) {
        String title = element.select(".productInfo>h3>a").text();
        String cleanedUnitPrice = cleanPrice(element.select(".pricing .pricePerUnit").text());
        BigDecimal unitPrice = BigDecimal.valueOf(Float.parseFloat(cleanedUnitPrice));

        String href = element.select(".productInfo>h3>a").attr("href");
        Document productPage;
        try {
            productPage = Jsoup.connect(href).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new RipeFruit(title, getSizeFromProductPage(productPage), unitPrice, getDescriptionFromProductPage(productPage));
    }

    private String cleanPrice(String text) {
        return text.replace("&pound", "").replace("/unit", "");
    }

    private String getDescriptionFromProductPage(Document productPage) {
        String description;
        description = productPage.select(".productText").text();
        return description;
    }

    private Float getSizeFromProductPage(Document productPage) {
        return Float.valueOf(productPage.text().getBytes().length)/1000;
    }

    public String getScrapeResultsAsString() {
        return  ripeFruitHtmlRepository.getRipeFruitHtml();
    }
}
