package com.upgpaint.powerbi.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
@Entity
@Table(name="VBPA")
public class VBPAEntity extends AuditEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name=" billing_document")
  private long billingDocument;
  @Column(name="billing_date")
  private LocalDate billingDate;
  @Column(name="partner")
  private String partner;
  @Column(name="business_partner")
  private String businessPartner;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public long getBillingDocument() {
	return billingDocument;
}
public void setBillingDocument(long billingDocument) {
	this.billingDocument = billingDocument;
}
public LocalDate getBillingDate() {
	return billingDate;
}
public void setBillingDate(LocalDate billingDate) {
	this.billingDate = billingDate;
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
public VBPAEntity() {
	super();
	// TODO Auto-generated constructor stub
}
public VBPAEntity(int id, long billingDocument, LocalDate billingDate, String partner, String businessPartner) {
	super();
	this.id = id;
	this.billingDocument = billingDocument;
	this.billingDate = billingDate;
	this.partner = partner;
	this.businessPartner = businessPartner;
}

  
  
  
}
