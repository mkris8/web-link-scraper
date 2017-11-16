package com.exonar.web.scraper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("webLinks")
public class WebLinks implements Serializable {

	private static final long serialVersionUID = 794121883872619351L;

	private String id;
	private String description;
	private LinkedHashMap<Integer,String> content;

	public WebLinks(@Value("links") String id, @Value("All the links on the webpage") String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setContent(LinkedHashMap<Integer,String> links) {
		this.content = links;
	}

	public LinkedHashMap<Integer, String> getContent() {
		return content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
