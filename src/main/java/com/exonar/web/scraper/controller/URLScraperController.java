package com.exonar.web.scraper.controller;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exonar.web.scraper.model.WebLinks;
import com.exonar.web.scraper.service.URLScraperManager;

@RestController
public class URLScraperController {
	
	private static final Logger logger = LoggerFactory.getLogger(URLScraperController.class);
	
    private final String requestMappingValue = "/link/{URL:.+}/**";
    private final String defaultError = "Please provide a valid input.";
    
    private Integer linkId;
   
    @Autowired
    URLScraperManager urlManager;
    
    @RequestMapping(value = requestMappingValue, method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody WebLinks getLinks(@PathVariable("URL") String URL) {
    	linkId = 0 ;
    	logger.info("Start getLinks. URL="+URL);
		LinkedHashMap<Integer, String> links = new LinkedHashMap<Integer,String>();
		
		return urlManager.scrapeURL(URL, links, linkId);
	}	
    
    @RequestMapping("*")
    @ResponseBody
    public String fallbackMethod(){
    	return defaultError;
    }

}
