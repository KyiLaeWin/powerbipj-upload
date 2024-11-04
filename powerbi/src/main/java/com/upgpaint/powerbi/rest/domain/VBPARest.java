package com.upgpaint.powerbi.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VBPARest {
	
	private static final Logger log = LoggerFactory.getLogger(VBPARest.class);
	
   @JsonProperty("Billing")
   private String billingDocument;
   @JsonProperty("Billing_Date")
   private LocalDate billingDate;
   @JsonProperty("Partner_Type")
   private String partner;
   @JsonProperty("Business_partner")
   private String businessPartner;	
   public VBPARest() {
	super();
	// TODO Auto-generated constructor stub
}

public VBPARest(String billingDocument, LocalDate billingDate, String partner, String businessPartner) {
	super();
	this.billingDocument = billingDocument;
	this.billingDate = billingDate;
	this.partner = partner;
	this.businessPartner = businessPartner;
}
public String getBillingDocument() {
	return billingDocument;
}
public void setBillingDocument(String billingDocument) {
	this.billingDocument = billingDocument;
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

public String getPartner() {
	return partner;
}
public void setPartner(String partner) {
	this.partner = partner;
}
public String getBusinessPartner() {
	return businessPartner;
}
public void setBusinessPartner(String businessPartner) {
	this.businessPartner = businessPartner;
}
   
   
}
