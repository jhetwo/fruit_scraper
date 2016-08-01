package repository;


import feign.RequestLine;

public interface RipeFruitHtmlRepository {
    @RequestLine("GET /2015_Developer_Scrape/5_products.html")
    String getRipeFruitHtml();
}
