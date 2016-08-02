package application;

import com.google.gson.Gson;
import controller.ScrapeController;
import scraper.ResultsPageScraper;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        Gson gson = new GsonFactory().get();
        ResultsPageScraper scraper = new ResultsPageScraper();

        ScrapeController scrapeController = new ScrapeController(scraper, gson);
        String results = scrapeController.getScrapeAsJson();

        System.out.println(results);
        try {
            System.out.println("Writing to output.json...");
            FileWriter fileWriter = new FileWriter("results.json");
            fileWriter.write(results);
            fileWriter.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
