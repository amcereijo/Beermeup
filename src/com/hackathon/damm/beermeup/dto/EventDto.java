package com.hackathon.damm.beermeup.dto;

import java.io.Serializable;
import java.util.List;

public class EventDto implements Serializable{

	private static final long serialVersionUID = -5984382850118182426L;
	
	private String title;
	private String info;
	private List<String> bands;
	private int eventImageId;
	private String footer;
	
	public static final String EVENT_INFO_KEY = "dayliInfoDto";
	
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
	public List<String> getBands() {
		return bands;
	}
	public void setBands(List<String> bands) {
		this.bands = bands;
	}
	public int getEventImageId() {
		return eventImageId;
	}
	public void setEventImageId(int eventImageId) {
		this.eventImageId = eventImageId;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	
}
