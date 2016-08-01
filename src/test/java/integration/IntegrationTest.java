package integration;


import application.FruitScraperApplication;
import helper.TestHelper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import scraper.WebScraper;

import java.io.IOException;

public class IntegrationTest {

    private final TestHelper testHelper = new TestHelper();
    private FruitScraperApplication fruitScraperApplication;
    private String expected;
    private WebScraper scraper;

    @Before
    public void beforeEach() throws IOException {
        scraper = new WebScraper();
        fruitScraperApplication = new FruitScraperApplication(scraper);

        expected = testHelper.getStringFromFile("src/test/resources/expectedIntegrationTestOutput.json");
    }

    @Test
    public void givenScraperCreatedWithOutput_whenScrapeCalled_thenCorrectJsonShouldBeReturned() throws IOException, JSONException {
        // This test is the first written and should be the last to pass.  In this instance there is a degree of uncertainty
        // as to the final form of the output and the implementation as I'm working with unfamiliar libraries, as such this
        // test is likely to evolve with the code.

        String actual = fruitScraperApplication.scrape();
        JSONAssert.assertEquals(expected, actual, true);
    }

}
