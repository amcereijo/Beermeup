package com.hackathon.damm.beermeup.dto;

import java.io.Serializable;
import java.util.List;

public class EventDto implements Serializable{

	private static final long serialVersionUID = -5984382850118182426L;
	
	private String title;
	private String info;
	private List<String> musicians;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<String> getMusicians() {
		return musicians;
	}
	public void setMusicians(List<String> musicians) {
		this.musicians = musicians;
	}
	
}
