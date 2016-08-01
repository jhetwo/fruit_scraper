package application;

import helper.TestHelper;
import model.FruitResults;
import model.RipeFruit;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import scraper.WebScraper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class FruitScraperApplicationTest {
    private FruitScraperApplication fruitScraperApplication;
    private final RipeFruit apple = new RipeFruit("Apple", 4.0f, BigDecimal.ONE, "An apple");
    private FruitResults results = new FruitResults(Collections.singletonList(apple), BigDecimal.ONE);

    @Mock
    private WebScraper scraper;
    private TestHelper testHelper = new TestHelper();
    private String expectedJson;

    @Before
    public void beforeEach() throws IOException {
        initMocks(this);
        fruitScraperApplication = new FruitScraperApplication(scraper);
        expectedJson = testHelper.getStringFromFile("src/test/resources/singleAppleResults.json");

        when(scraper.getScrapeResults()).thenReturn(results);
    }

    @Test
    public void givenWebScrapeSuccess_whenScrapeCalled_thenReturnCorrectlyMarshalledJson() throws JSONException {
        String actual = fruitScraperApplication.scrape();
        JSONAssert.assertEquals(actual, expectedJson, false);
    }
}