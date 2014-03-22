package com.hackathon.damm.beermeup.locate;

import java.io.Serializable;

public class Beer implements Serializable {
	private static final long serialVersionUID = 1390749674962152987L;
	public static final String BEER_KEY = "beerKey";
	
	private String name;
	private BeerType beerType;
	private int beerImageId;

	public Beer(){
	}
	
	public Beer(BeerType beerType, String name, int beerImageId){
		this.beerType = beerType;
		this.name = name;
		this.beerImageId = beerImageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBeerImageId() {
		return beerImageId;
	}

	public void setBeerImageId(int beerImageId) {
		this.beerImageId = beerImageId;
	}

	public BeerType getBeerType() {
		return beerType;
	}

	public void setBeerType(BeerType beerType) {
		this.beerType = beerType;
	}
}
