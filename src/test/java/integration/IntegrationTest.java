package integration;


import application.FruitScraperController;
import helper.TestHelper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import scraper.WebScraper;

import java.io.IOException;

public class IntegrationTest {

    private final TestHelper testHelper = new TestHelper();
    private FruitScraperController fruitScraperController;
    private String expected;
    private WebScraper scraper;

    @Before
    public void beforeEach() throws IOException {
        scraper = new WebScraper();
        fruitScraperController = new FruitScraperController(scraper);

        expected = testHelper.getStringFromFile("src/test/resources/expectedIntegrationTestOutput.json");
    }

    @Test
    public void givenScraperCreatedWithOutput_whenScrapeCalled_thenCorrectJsonShouldBeReturned() throws IOException, JSONException {
        // This test is the first written and should be the last to pass.  In this instance there is a degree of uncertainty
        // as to the final form of the output and the implementation as I'm working with unfamiliar libraries, as such this
        // test is likely to evolve with the code.
        // At the time of writing I'm undecided whether to run the test against static data or use the live endpoint in
        // the interests of time. Obviously I would only ever use static data in a production system.

        String actual = fruitScraperController.scrape();
        System.out.println(actual);
        JSONAssert.assertEquals(expected, actual, true);
    }

}
