package com.upgpaint.powerbi.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Material")
public class Material extends AuditEntity{
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id")
	  private int id;
	  
	  @Column(name="material_number")
	  private String materialNumber;
	  @Column(name="material_desc")
	  private String materialDesc;
	  @Column(name="material_type")
	  private String materialType;
	  @Column(name="material_gp")
	  private String materialGp;
	  @Column(name="brand")
	  private String brand;
	  @Column(name="buom")
	  private String bUOM;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getMaterialGp() {
		return materialGp;
	}
	public void setMaterialGp(String materialGp) {
		this.materialGp = materialGp;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getbUOM() {
		return bUOM;
	}
	public void setbUOM(String bUOM) {
		this.bUOM = bUOM;
	}
	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Material(int id, String materialNumber, String materialDesc, String materialType, String materialGp,
			String brand, String bUOM) {
		super();
		this.id = id;
		this.materialNumber = materialNumber;
		this.materialDesc = materialDesc;
		this.materialType = materialType;
		this.materialGp = materialGp;
		this.brand = brand;
		this.bUOM = bUOM;
	}
	  
	  

}
