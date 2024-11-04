package com.upgpaint.powerbi.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "cost_center", uniqueConstraints = { @UniqueConstraint(columnNames = "CC_No") })
public class CostCenter extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	@Column(name = "CC_Group1")
	private String CC_Group1;

	@Column(name = "CC_Group1_Desc")
	private String CC_Group1_Desc;

	@Column(name = "CC_Group2")
	private String CC_Group2;

	@Column(name = "CC_Group2_Desc")
	private String CC_Group2_Desc;

	@Column(name = "CC_No")
	private long CC_No;

	@Column(name = "CC_Desc")
	private String CC_Desc;

	@Column(name = "CC_OrgUnit")
	private String CC_OrgUnit;

//	@Column(name="ECC_CC_No")
//	private long ECC_CC_No;
//


	public CostCenter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CostCenter(long id, String cC_Group1, String cC_Group1_Desc, String cC_Group2, String cC_Group2_Desc,
			long cC_No, String cC_Desc, String cC_OrgUnit, long eCC_CC_No) {
		super();
		this.id = id;
		CC_Group1 = cC_Group1;
		CC_Group1_Desc = cC_Group1_Desc;
		CC_Group2 = cC_Group2;
		CC_Group2_Desc = cC_Group2_Desc;
		CC_No = cC_No;
		CC_Desc = cC_Desc;
		CC_OrgUnit = cC_OrgUnit;
		//ECC_CC_No = eCC_CC_No;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCC_Group1() {
		return CC_Group1;
	}

	public void setCC_Group1(String cC_Group1) {
		CC_Group1 = cC_Group1;
	}

	public String getCC_Group1_Desc() {
		return CC_Group1_Desc;
	}

	public void setCC_Group1_Desc(String cC_Group1_Desc) {
		CC_Group1_Desc = cC_Group1_Desc;
	}

	public String getCC_Group2() {
		return CC_Group2;
	}

	public void setCC_Group2(String cC_Group2) {
		CC_Group2 = cC_Group2;
	}

	public String getCC_Group2_Desc() {
		return CC_Group2_Desc;
	}

	public void setCC_Group2_Desc(String cC_Group2_Desc) {
		CC_Group2_Desc = cC_Group2_Desc;
	}

	public long getCC_No() {
		return CC_No;
	}

	public void setCC_No(long cC_No) {
		CC_No = cC_No;
	}

	public String getCC_Desc() {
		return CC_Desc;
	}

	public void setCC_Desc(String cC_Desc) {
		CC_Desc = cC_Desc;
	}

	public String getCC_OrgUnit() {
		return CC_OrgUnit;
	}

	public void setCC_OrgUnit(String cC_OrgUnit) {
		CC_OrgUnit = cC_OrgUnit;
	}

//	public long getECC_CC_No() {
//		return ECC_CC_No;
//	}
//
//	public void setECC_CC_No(long eCC_CC_No) {
//		ECC_CC_No = eCC_CC_No;
//	}




}
