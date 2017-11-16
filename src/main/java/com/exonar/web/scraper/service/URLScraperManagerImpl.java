package com.exonar.web.scraper.service;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exonar.web.scraper.model.WebLinks;

@Service ("urlManager")
public class URLScraperManagerImpl implements URLScraperManager {

	private static final Logger logger = LoggerFactory.getLogger(URLScraperManagerImpl.class);
	private final String httpPrefix = "http://";

	@Autowired
	WebLinks webLinks;
	
	@Override
	public WebLinks scrapeURL(String URL, LinkedHashMap<Integer,String> links, Integer linkId) {

			try {

				if(!links.containsKey(linkId)) {
					links.put(linkId, URL);
					linkId ++ ;
				}
				
				Document document = Jsoup.connect( httpPrefix + URL).get();
				Elements linksOnPage = document.select("a[href]");

				for (Element page : linksOnPage) {
					scrapeURL(page.attr("abs:href"), links, linkId ++);
				}
			} catch (IOException e) {
				logger.info("For '" + URL + "': " + e.getMessage());
			}

		webLinks.setContent(links);
		return webLinks;
	}

}
