package com.example.demo.dto;

import java.io.Serializable;

public class CovidDataDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2583173589703916129L;
	
	public String country = null;
	public Integer cases = null;
	public Integer deaths = null;
	public Integer recoveries = null;
	
	public CovidDataDto(String country, Integer cases, Integer deaths, Integer recoveries) {		
		this.country = country;
		this.cases = cases;
		this.deaths = deaths;
		this.recoveries = recoveries;
	}

	public CovidDataDto() {
		super();
	}
	
	
}
