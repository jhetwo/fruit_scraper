package application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import controller.ScrapeController;
import helper.TestHelper;
import model.FileSize;
import model.FruitResults;
import model.RipeFruit;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import scraper.ResultsPageScraper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class ScrapeControllerTest {
    private ScrapeController scrapeController;
    private final RipeFruit apple = new RipeFruit("Apple", 4.0f, BigDecimal.ONE, "An apple");
    private FruitResults results = new FruitResults(Collections.singletonList(apple), BigDecimal.ONE);

    @Mock
    private ResultsPageScraper scraper;
    private TestHelper testHelper = new TestHelper();
    private String expectedJson;

    @Before
    public void beforeEach() throws IOException {
        initMocks(this);
        scrapeController = new ScrapeController(scraper, new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(BigDecimal.class,
                        (JsonSerializer<BigDecimal>) (src, typeOfSrc, context) -> new JsonPrimitive(src.setScale(2, BigDecimal.ROUND_UP)))
                .registerTypeAdapter(FileSize.class,
                        (JsonSerializer<FileSize>) (src, typeOfSrc, context) -> new JsonPrimitive(new DecimalFormat("0.##kb").format(src.getSize())))
                .create());
        expectedJson = testHelper.getStringFromFile("src/test/resources/singleAppleResults.json");

        when(scraper.getScrapeResults()).thenReturn(results);
    }

    @Test
    public void givenWebScrapeSuccess_whenScrapeCalled_thenReturnCorrectlyMarshalledJson() throws JSONException {
        String actual = scrapeController.getScrapeAsJson();
        JSONAssert.assertEquals(actual, expectedJson, false);
    }
}