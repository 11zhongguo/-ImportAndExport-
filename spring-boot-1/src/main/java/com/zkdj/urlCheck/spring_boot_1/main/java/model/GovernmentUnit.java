package com.zkdj.urlCheck.spring_boot_1.main.java.model;

import org.springframework.data.annotation.Transient;

/**
 * @author sixiujun
 *
 */
public class GovernmentUnit {

	private Integer id;
	
	private String website;
	
	private String homePageUrl;
	
	private String unit;
	
	private String webState;

	private String briefUrl;
	
	private String unitCity;
	
	private String unitName;
	
	@Transient  
	private   String competentUnits;
	
	@Transient  
	private   Integer level;
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCompetentUnits() {
		return competentUnits;
	}

	public void setCompetentUnits(String competentUnits) {
		this.competentUnits = competentUnits;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWebState() {
		return webState;
	}

	public void setWebState(String webState) {
		this.webState = webState;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public String getBriefUrl() {
		return briefUrl;
	}

	public void setBriefUrl(String briefUrl) {
		this.briefUrl = briefUrl;
	}

	public String getUnitCity() {
		return unitCity;
	}

	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	

	
}
