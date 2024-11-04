package com.upgpaint.powerbi.rest.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MaterialRest {

	private static final Logger log = LoggerFactory.getLogger(MaterialRest.class);
	

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
	
	public MaterialRest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public MaterialRest(String materialNumber, String materialDesc, String materialType, String materialGrp,
			String brand, String bUOM) {
		super();
		MaterialNumber = materialNumber;
		MaterialDesc = materialDesc;
		MaterialType = materialType;
		MaterialGrp = materialGrp;
		Brand = brand;
		BUOM = bUOM;
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
