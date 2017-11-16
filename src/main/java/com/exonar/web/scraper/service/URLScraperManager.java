package com.exonar.web.scraper.service;

import java.util.LinkedHashMap;

import com.exonar.web.scraper.model.WebLinks;

public interface URLScraperManager {

	public WebLinks scrapeURL(String URL, LinkedHashMap<Integer, String> links, Integer linkId);
	
}
