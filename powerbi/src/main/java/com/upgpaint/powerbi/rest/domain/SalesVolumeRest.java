package com.upgpaint.powerbi.rest.domain;



import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upgpaint.powerbi.db.service.impl.SalesVolumeDbServiceImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalesVolumeRest {
	
	private static final Logger log = LoggerFactory.getLogger(SalesVolumeRest.class);

	@JsonProperty("Bill_Document")
    private String billingDocument;
    @JsonProperty("Billing_Category")
    private String billingCategory;
    @JsonProperty("Sales_Group")
    private String salesGroup;
    @JsonProperty("Sales_Office")
    private String salesOffice;
 //   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    @JsonProperty("BILLLING_DATE")
    private LocalDate billingDate; 
    @JsonProperty("Sales_Org")
    private String saleOrg;
    @JsonProperty("Region")
    private String region;
    @JsonProperty("Dist_Channel")
    private String distChannel;
    @JsonProperty("Volume")
    private String volume;
    @JsonProperty("V_Unit")
    private String volumeUnit;
    @JsonProperty("Material")
    private String materialNo;
    @JsonProperty("Plant")
    private String plant;
    @JsonProperty("Storage_Loc")
    private String storageLoc;
    @JsonProperty("Net_Value")
    private Double netValue;
    @JsonProperty("Rebate_Basis")
    private Double rebateBasis;
    @JsonProperty("Output_Tax")
    private Double outputTax;
    @JsonProperty("CSD_Point")
    private Double csdPoint;
   
   public SalesVolumeRest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

   public SalesVolumeRest(String billingDocument, String billingCategory, String salesGroup, String salesOffice,
			LocalDate billingDate, String saleOrg, String region, String distChannel, String volume, String volumeUnit,
			String materialNo, String plant, String storageLoc, Double netValue, Double rebateBasis, Double outputTax, Double csdPoint) {
		super();
		this.billingDocument = billingDocument;
		this.billingCategory = billingCategory;
		this.salesGroup = salesGroup;
		this.salesOffice = salesOffice;
		this.billingDate = billingDate;
		this.saleOrg = saleOrg;
		this.region = region;
		this.distChannel = distChannel;
		this.volume = volume;
		this.volumeUnit = volumeUnit;
		this.materialNo = materialNo;
		this.plant = plant;
		this.storageLoc = storageLoc;
		this.netValue = netValue;
		this.rebateBasis = rebateBasis;
		this.outputTax = outputTax;
		this.csdPoint = csdPoint;
	}	
	


	public String getBill_Document() {
		return billingDocument;
	}

	public void setBill_Document(String bill_Document) {
		this.billingDocument = bill_Document;
	}
		public String getBilling_Category() {
		return billingCategory;
	}
	public void setBilling_Category(String billing_Category) {
		this.billingCategory = billing_Category;
	}
	public String getSales_Group() {
		return salesGroup;
	}
	public void setSales_Group(String sales_Group) {
		this.salesGroup = sales_Group;
	}
	public String getSales_Office() {
		return salesOffice;
	}
	public void setSales_Office(String sales_Office) {
		this.salesOffice = sales_Office;
	}
/*	public String getBilling_Date() {
		return billingDate;
	}
	public void setBilling_Date(String billing_Date) {
		this.billingDate = billing_Date;
	}
	*/
	

   
    
	public String getSale_org() {
		return saleOrg;
	}
	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String odataDate) {
        log.info("Inside setBilling_Date method with OData string: {}", odataDate);
        if (odataDate != null && !odataDate.isEmpty()) {
            this.billingDate = parseODataDate(odataDate);  // Parse and set LocalDate
            log.info("Parsed Billing Date: {}", billingDate);
        } else {
            this.billingDate = null;  // Handle null or empty date
            log.warn("Billing Date String was null or empty.");
        }
	}


	private LocalDate parseODataDate(String odataDate) {
        try {
            // Extract the timestamp from "/Date(1727049600000)/"
            long timestamp = Long.parseLong(odataDate.replaceAll("\\D", ""));
            // Convert timestamp to LocalDate
            return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (NumberFormatException e) {
            log.error("Failed to parse OData date: {}, error: {}", odataDate, e.getMessage());
            return null;
        }
    }


	public void setSale_org(String sale_org) {
		this.saleOrg = sale_org;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDist_Channel() {
		return distChannel;
	}
	public void setDist_Channel(String dist_Channel) {
		this.distChannel = dist_Channel;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getV_unit() {
		return volumeUnit;
	}
	public void setV_unit(String v_unit) {
		this.volumeUnit = v_unit;
	}
	public String getMaterial() {
		return materialNo;
	}
	public void setMaterial(String material) {
		this.materialNo = material;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	public String getStorage_loc() {
		return storageLoc;
	}
	public void setStorage_loc(String storage_loc) {
		this.storageLoc = storage_loc;
	}
	
	public Double getNet_Value() {
		return netValue;
	}
	public void setNet_Value(Double net_Value) {
		this.netValue = net_Value;
	}
	public Double getRebate_Basis() {
		return rebateBasis;
	}
	public void setRebate_Basis(Double rebate_Basis) {
		this.rebateBasis = rebate_Basis;
	}

	public Double getOutput_tax() {
		return outputTax;
	}
	public void setOutput_tax(Double output_tax) {
		this.outputTax = output_tax;
	}

		public Double getCsdPoint() {
		return csdPoint;
	}


	public void setCsdPoint(Double csdPoint) {
		this.csdPoint = csdPoint;
	}


		public boolean contains(String billingDocument2) {
			// TODO Auto-generated method stub
			return true;
		}
	   

	    
}