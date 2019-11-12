package com.zkdj.urlCheck.spring_boot_1.main.java.model;

import java.util.Date;

import org.springframework.data.annotation.Transient;

/**
 * @author sixiujun
 *
 */
public class GatherRulesSource {

	private Integer id;
	
	private String regularName;
	
	private String indexUrl;
	
	private String attributionInformation;
	
	private Integer level;

	private String websiteNature;
	
	private String competentUnits;
	
	private Integer configurationNumber;
	
	private Date maintenanceTime;

	private Integer contryId;
	
	private Integer carrierID;
	
	private String province;
	
	private String city;
	
	private String district;
	
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getContryId() {
		return contryId;
	}

	public void setContryId(Integer contryId) {
		this.contryId = contryId;
	}

	public Integer getCarrierID() {
		return carrierID;
	}

	public void setCarrierID(Integer carrierID) {
		this.carrierID = carrierID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegularName() {
		return regularName;
	}

	public void setRegularName(String regularName) {
		this.regularName = regularName;
	}


	public String getIndexUrl() {
		return indexUrl;
	}

	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}

	public String getAttributionInformation() {
		return attributionInformation;
	}

	public void setAttributionInformation(String attributionInformation) {
		this.attributionInformation = attributionInformation;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getWebsiteNature() {
		return websiteNature;
	}

	public void setWebsiteNature(String websiteNature) {
		this.websiteNature = websiteNature;
	}

	public String getCompetentUnits() {
		return competentUnits;
	}

	public void setCompetentUnits(String competentUnits) {
		this.competentUnits = competentUnits;
	}

	public Integer getConfigurationNumber() {
		return configurationNumber;
	}

	public void setConfigurationNumber(Integer configurationNumber) {
		this.configurationNumber = configurationNumber;
	}

	public Date getMaintenanceTime() {
		return maintenanceTime;
	}

	public void setMaintenanceTime(Date maintenanceTime) {
		this.maintenanceTime = maintenanceTime;
	}

	@Override
	public String toString() {
		return "GatherRulesSource [id=" + id + ", regularName=" + regularName + ", indexURL=" + indexUrl
				+ ", attributionInformation=" + attributionInformation + ", level=" + level + ", websiteNature="
				+ websiteNature + ", competentUnits=" + competentUnits + ", configurationNumber=" + configurationNumber
				+ ", maintenanceTime=" + maintenanceTime + "]";
	}
	


	
}
