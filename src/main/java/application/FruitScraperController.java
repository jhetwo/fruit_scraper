package application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.FruitResults;
import model.RipeFruit;
import model.json.RipeFruitTypeAdapter;
import scraper.WebScraper;

public class FruitScraperController {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(RipeFruit.class, new RipeFruitTypeAdapter())
            // TODO: Results total to 2 dp
            .create();
    private final WebScraper scraper;

    public FruitScraperController(WebScraper scraper) {
        this.scraper = scraper;
    }

    public String scrape() {
        FruitResults results = scraper.getScrapeResults();
        return gson.toJson(results);
    }

}
