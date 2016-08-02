package integration;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import controller.ScrapeController;
import helper.TestHelper;
import model.FileSize;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import scraper.ResultsPageScraper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class IntegrationTest {

    private final TestHelper testHelper = new TestHelper();
    private ScrapeController scrapeController;
    private String expected;
    private ResultsPageScraper scraper;

    @Before
    public void beforeEach() throws IOException {
        scraper = new ResultsPageScraper();
        scrapeController = new ScrapeController(scraper, new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(BigDecimal.class,
                        (JsonSerializer<BigDecimal>) (src, typeOfSrc, context) -> new JsonPrimitive(src.setScale(2, BigDecimal.ROUND_UP)))
                .registerTypeAdapter(FileSize.class,
                        (JsonSerializer<FileSize>) (src, typeOfSrc, context) -> new JsonPrimitive(new DecimalFormat("0.##kb").format(src.getSize())))
                .create());

        expected = testHelper.getStringFromFile("src/test/resources/expectedIntegrationTestOutput.json");
    }

    @Test
    public void whenScrapeCalled_thenCorrectJsonShouldBeReturned() throws IOException, JSONException {
        // This test is the first written and should be the last to pass.  It's currently running against the real
        // endpoint which is a very bad idea in real life. I've done it here in the interests of brevity and to prevent
        // having to pull in more dependencies such as WireMock.

        String actual = scrapeController.getScrapeAsJson();
        JSONAssert.assertEquals(expected, actual, true);
    }

}
