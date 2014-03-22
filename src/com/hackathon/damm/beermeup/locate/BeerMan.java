package com.hackathon.damm.beermeup.locate;

import java.util.List;

public class BeerMan {
	
	private List<BeerType> beerTypes;
	private Position position;

	public BeerMan(){
		
	}
	
	public BeerMan(Position position, List<BeerType> beerTypes){
		this.position = position;
		this.beerTypes = beerTypes;
	}

	public List<BeerType> getBeerTypes() {
		return beerTypes;
	}

	public void setBeerTypes(List<BeerType> beerTypes) {
		this.beerTypes = beerTypes;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
}
