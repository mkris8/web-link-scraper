# web-link-scraper
#Overview

The web-link-scraper REST API is built using SPRING BOOT.

Junit / REST Assured  is used for testing and validating the REST API.

jSoup is used for web scraping.

A REST client like POSTMAN can be used to send requests and inspect the response.

Project compliance JRE 1.8

#Installation

Git clone https://github.com/mkris8/web-link-scraper.git

1) Import the source into an IDE of choice
2) mvn clean
3) mvn install
4) Start as spring boot application. All the API calls can be tested using any REST client.


#To run the integration tests :
5) Run all of the above steps.
6) Right click /src/test/java/com/exonar/web/scraper/controller/URLScraperControllerTest.java and run as jUnit.


The API is be able to execute the following requests:

GET - Get all the contained links in a web page

REST Endpoint
 The endpoint is /link

#Example Requests

http://localhost:8080/link/news.google.com
http://localhost:8080/link/google.com
http://localhost:8080/link/www.espn.com


#Example Response

{
    "id": "links",
    "description": "All the links on the webpage",
    "content": {
        "0": "swapi.co",
        "1": "https://twitter.com/share",
        "2": "https://swapi.co/",
        "3": "https://swapi.co/about",
        "4": "https://swapi.co/documentation",
        "5": "https://swapi.co/#",
        "6": "https://swapi.co/#",
        "7": "https://swapi.co/#",
        "8": "https://swapi.co/documentation",
        "9": "https://github.com/phalt/swapi",
        "10": "https://phalt.co?ref=swapi",
        "11": "https://twitter.com/phalt_"
    }
}
