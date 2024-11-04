package com.upgpaint.powerbi.db.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
//import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="Salesvolume")
public class Salesvolume extends AuditEntity{
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-mm-yyyy");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	private long id;

	@Column(name="billing_document")
    private long billingDocument;

	@Column(name="billing_category")
    private String billingCategory;

	@Column(name="sales_group")
    private String salesGroup;

	@Column(name="sales_office")
    private String salesOffice;

//	@Column(name="billing_date")
//	@Temporal(TemporalType.TIMESTAMP)
//   private String billingDate;
	
	@Column(name = "billing_date", columnDefinition = "DATE")
    private LocalDate billingDate;

	@Column(name="sales_org")
    private String salesOrg;

	@Column(name="region")
    private String region;

	@Column(name="dist_channel")
    private String distChannel;

	@Column(name="volume")
    private String volume;

	@Column(name="material_number")
	 private String materialNo;

	@Column(name="volume_unit")
	private String volumeUnit;

    @Column(name="plant")
    private String Plant;
    
    @Column(name="storage_loc")
    private String storageLoc;

    @Column(name="net_value")
    private Double Net_Value;

	@Column(name="rebate_basis")
	private Double rebateBasis;

    @Column(name="output_tax")
	private Double outputTax;
	
	@Column(name="csd_point")
	private Double csd_point;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBillingDocument() {
		return billingDocument;
	}

	public void setBillingDocument(Long billingDocument) {
		this.billingDocument = billingDocument;
	}

	public String getBillingCategory() {
		return billingCategory;
	}

	public void setBillingCategory(String billingCategory) {
		this.billingCategory = billingCategory;
	}

	public String getSalesGroup() {
		return salesGroup;
	}

	public void setSalesGroup(String salesGroup) {
		this.salesGroup = salesGroup;
	}

	public String getSalesOffice() {
		return salesOffice;
	}

	public void setSalesOffice(String salesOffice) {
		this.salesOffice = salesOffice;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistChannel() {
		return distChannel;
	}

	public void setDistChannel(String distChannel) {
		this.distChannel = distChannel;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getMaterialNo() {
		return materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public String getVolumeUnit() {
		return volumeUnit;
	}

	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	public String getPlant() {
		return Plant;
	}

	public void setPlant(String plant) {
		Plant = plant;
	}



	public String getStorageLoc() {
		return storageLoc;
	}

	public void setStorageLoc(String storageLoc) {
		this.storageLoc = storageLoc;
	}

	

	public Double getNet_Value() {
		return Net_Value;
	}

	public void setNet_Value(Double net_Value) {
		Net_Value = net_Value;
	}

	public Double getRebateBasis() {
		return rebateBasis;
	}

	public void setRebateBasis(Double rebateBasis) {
		this.rebateBasis = rebateBasis;
	}


	


	public Double getOutputTax() {
		return outputTax;
	}

	public void setOutputTax(Double outputTax) {
		this.outputTax = outputTax;
	}
	
/*	public Double getCsd_point() {
		return csd_point;
	}

	public void setCsd_point(Double csd_point) {
		this.csd_point = csd_point;
	}*/
	
	
	
	public static SimpleDateFormat getDateFormat() {
		return DATE_FORMAT;
	}
	
	public Double getCsd_point() {
		return csd_point;
	}

	public void setCsd_point(Double csd_point) {
		this.csd_point = csd_point;
	}

	public Salesvolume() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Salesvolume(long id, long billingDocument, String billingCategory, String salesGroup, String salesOffice,
			LocalDate billingDate, String salesOrg, String region, String distChannel, String volume, String materialNo,
			String volumeUnit, String plant, String storageLoc, Double net_Value, Double rebateBasis, Double outputTax,
			Double csd_point) {
		super();
		this.id = id;
		this.billingDocument = billingDocument;
		this.billingCategory = billingCategory;
		this.salesGroup = salesGroup;
		this.salesOffice = salesOffice;
		this.billingDate = billingDate;
		this.salesOrg = salesOrg;
		this.region = region;
		this.distChannel = distChannel;
		this.volume = volume;
		this.materialNo = materialNo;
		this.volumeUnit = volumeUnit;
		Plant = plant;
		this.storageLoc = storageLoc;
		Net_Value = net_Value;
		this.rebateBasis = rebateBasis;
		this.outputTax = outputTax;
		this.csd_point = csd_point;
	}

	
}
