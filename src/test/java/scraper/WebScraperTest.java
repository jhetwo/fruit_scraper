package scraper;

import helper.TestHelper;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class WebScraperTest {



    @Test
    public void sanityTest() throws IOException {
        WebScraper webScraper = new WebScraper();
        assertThat(webScraper.getScrapeResultsAsString()).isEqualTo(new TestHelper().getStringFromFile("src/test/resources/expected.html"));
    }
}