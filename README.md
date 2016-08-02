### README

To build: `gradle build`
To run: `gradle test`
To run: `gradle run`

The first class written was the integration test, though it changed significantly as my understanding of the problem gre. Time pressure lead to prioritising functional code over unit test coverage, as such it's not as high as it should be, normally I would have included fine grained unit tests for all classes.  Given the very limited scope of the application though the integration test alone is adequate in my opinion, further testing would only bring benefit if further functionality were introduced for regression purposes and to demonstrate usage.
  
One of the first decisions I made, before I'd realised how tight time was, was to create a domain model. In practice this does bring benefits even in a trivial application but would be essential in an application with greater scope. I stopped short of introducing ports and adapters to allow swapping out of components, for example the scraper or console interface, but this would again be a priority in a non trivial application.  The WebScraper class could be easily be implemented as an interface to allow scraping of other sites, or even retrieving directly from a data store.


  
