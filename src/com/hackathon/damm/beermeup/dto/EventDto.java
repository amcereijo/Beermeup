package com.hackathon.damm.beermeup.dto;

import java.io.Serializable;

public class EventDto implements Serializable{

	private static final long serialVersionUID = -5984382850118182426L;
	
	private String title;
	private String info;
	
	
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

}
