package com.upgpaint.powerbi.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MMRest {
//	private static final Logger log = LoggerFactory.getLogger(MMRest.class);
	
	@JsonProperty("Material")
	private String MaterialNumber;
	@JsonProperty("Material_Description")
	private String MaterialDesc;
	@JsonProperty("Material_Type")
	private String MaterialType;
	@JsonProperty("Material_Grp")
	private String MaterialGrp;
	@JsonProperty("Brand")
	private String Brand;
	@JsonProperty("B_UOM")
	private String BUOM;
	
	public MMRest() {
		super();
	}
	
	public MMRest(String MaterialNumber,String MaterialDesc,String MaterialType,String MaterialGrp, String Brand,String BUOM) {
		super();
		this.MaterialNumber=MaterialNumber;
		this.MaterialDesc=MaterialDesc;
		this.MaterialType=MaterialType;
		this.MaterialGrp=MaterialGrp;
		this.Brand=Brand;
		this.BUOM=BUOM;
	}

	public String getMaterialNumber() {
		return MaterialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		MaterialNumber = materialNumber;
	}

	public String getMaterialDesc() {
		return MaterialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		MaterialDesc = materialDesc;
	}

	public String getMaterialType() {
		return MaterialType;
	}

	public void setMaterialType(String materialType) {
		MaterialType = materialType;
	}

	public String getMaterialGrp() {
		return MaterialGrp;
	}

	public void setMaterialGrp(String materialGrp) {
		MaterialGrp = materialGrp;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getBUOM() {
		return BUOM;
	}

	public void setBUOM(String bUOM) {
		BUOM = bUOM;
	}
	
	
}