package integration;


import application.FruitScraperController;
import com.sun.deploy.util.StringUtils;
import org.junit.Before;
import org.junit.Test;
import scraper.WebScraper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {

    private FruitScraperController fruitScraperController;
    private String expected;
    private WebScraper scraper;

    @Before
    public void beforeEach() throws IOException {
        scraper = new WebScraper();
        fruitScraperController = new FruitScraperController(scraper);

        List<String> strings = Files.readAllLines(Paths.get("src/test/resources/expectedOutput.json"));
        expected = StringUtils.join(strings, "");
    }

    @Test
    public void givenScraperCreatedWithOutput_whenScrapeCalled_thenCorrectJsonShouldBeReturned() throws IOException {
        // This test is the first written and should be the last to pass.  In this instance there is a degree of uncertainty
        // as to the final form of the output and the implementation as I'm working with unfamiliar libraries, as such this
        // test is likely to evolve with the code.
        // At the time of writing I'm undecided whether to run the test against static data or use the live endpoint in
        // the interests of time. Obviously I would only ever use static data in a production system.

        String actual = fruitScraperController.scrape();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

}
