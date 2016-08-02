package scraper;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

class ProductPageScraper {
    private final Document productPage;

    ProductPageScraper(String href) {
        try {
            productPage = Jsoup.connect(href).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String getDescription() {
        String description = productPage.select(".productText>p").get(0).text();
        return StringEscapeUtils.escapeHtml4(description);
    }

    Float getSize() {
        final int sizeInBytes = productPage.text().getBytes().length;
        return (float) sizeInBytes /1000;
    }
}
