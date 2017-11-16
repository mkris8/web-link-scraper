package com.exonar.web.scraper.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayway.restassured.response.Response;

@SpringBootTest
@WebMvcTest(value = URLScraperController.class, secure = false)

public class URLScraperControllerTest {

	private final String URL = "/link/www.swapi.co";
	private final String INVALID_URL_1 = "/link";
	private final String INVALID_URL_2 = "/";
	private final String INVALID_URL_PREFIX = "/link/http://www.swapi.co";

	@Test
	public void basicPingTest() {
		given().when().get("/link/www.swapi.co").then().statusCode(200);
	}

	@Test
	public void testGetLinks() throws Exception {

		given().when().get(URL).then().body("id", equalTo("links"));
		given().when().get(URL).then().body("description", equalTo("All the links on the webpage"));

		Response response = given().when().get(URL).then().extract().response();
		String jsonAsString = response.asString();
		List<String> jsonAsArrayList = new ArrayList<String>(Arrays.asList(jsonAsString.split(",")));

		assertThat(jsonAsArrayList.size(), equalTo(14));

		given().when().get(URL).then().body("content.0", equalTo("www.swapi.co"));
		given().when().get(URL).then().body("content.1", equalTo("https://twitter.com/share"));
		given().when().get(URL).then().body("content.2", equalTo( "https://www.swapi.co/"));
		given().when().get(URL).then().body("content.3", equalTo("https://www.swapi.co/about"));
		given().when().get(URL).then().body("content.4", equalTo("https://www.swapi.co/documentation"));
		given().when().get(URL).then().body("content.5", equalTo("https://www.swapi.co/#"));
		given().when().get(URL).then().body("content.6", equalTo("https://www.swapi.co/#"));
		given().when().get(URL).then().body("content.7", equalTo("https://www.swapi.co/#"));
		given().when().get(URL).then().body("content.8", equalTo("https://www.swapi.co/documentation"));
		given().when().get(URL).then().body("content.9", equalTo("https://github.com/phalt/swapi"));
		given().when().get(URL).then().body("content.10", equalTo("https://phalt.co?ref=swapi"));
		given().when().get(URL).then().body("content.11", equalTo("https://twitter.com/phalt_"));
		
	}

	@Test
	public void invalidURL1CheckDefaultErrorResponse() {
		given().when().get("INVALID_URL_1").then().statusCode(200);
		given().when().get(INVALID_URL_1).then().body(containsString("Please provide a valid input."));
	}

	@Test
	public void invalidURL2CheckDefaultErrorResponse() {
		given().when().get("INVALID_URL_2").then().statusCode(200);
		given().when().get(INVALID_URL_2).then().body(containsString("Please provide a valid input."));
	}

	@Test
	public void noMessageAvailableForInvalidPrefix() {
		given().when().get(INVALID_URL_PREFIX).then().statusCode(400);
		given().when().get("//link/http://www.swapi.co").then().statusCode(400);
		given().when().get("/link//http://www.swapi.co").then().statusCode(400);
		given().when().get("/link/http:///www.swapi.co").then().statusCode(400);
		given().when().get("/link//http:///www.swapi.co").then().statusCode(400);
		given().when().get("//link//http:///www.swapi.co").then().statusCode(400);
	}

}
