package controller;

import com.google.gson.*;
import model.FruitResults;
import scraper.ResultsPageScraper;

public class ScrapeController {
    private final Gson gson;
    private final ResultsPageScraper scraper;

    public ScrapeController(ResultsPageScraper scraper, Gson gson) {
        this.scraper = scraper;
        this.gson = gson;
    }

    public String getScrapeAsJson() {
        FruitResults results = scraper.getScrapeResults();
        return gson.toJson(results);
    }
}
