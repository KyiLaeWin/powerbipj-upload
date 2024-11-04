package com.upgpaint.powerbi.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CostCenterRest {

	@JsonProperty("CC_Group1")
	private String ccGroup1;

	@JsonProperty("CC_Group1_Desc")
	private String ccGroup1Desc;

	@JsonProperty("CC_Group2")
	private String ccGroup2;

	@JsonProperty("CC_Group2_Desc")
	private String ccGroup2Desc;

	@JsonProperty("CC_NO")
	private String ccNo;

	@JsonProperty("CC_Desc")
	private String ccDesc;

	@JsonProperty("CC_OrgUnit")
	private String ccOrgUnit;

	public CostCenterRest() {
		super();
	}

	public CostCenterRest(String ccGroup1, String ccGroup1Desc, String ccGroup2, String ccGroup2Desc, String ccNo,
			String ccDesc, String ccOrgUnit) {
		super();
		this.ccGroup1 = ccGroup1;
		this.ccGroup1Desc = ccGroup1Desc;
		this.ccGroup2 = ccGroup2;
		this.ccGroup2Desc = ccGroup2Desc;
		this.ccNo = ccNo;
		this.ccDesc = ccDesc;
		this.ccOrgUnit = ccOrgUnit;
	}

	public String getCcGroup1() {
		return ccGroup1;
	}

	public void setCcGroup1(String ccGroup1) {
		this.ccGroup1 = ccGroup1;
	}

	public String getCcGroup1Desc() {
		return ccGroup1Desc;
	}

	public void setCcGroup1Desc(String ccGroup1Desc) {
		this.ccGroup1Desc = ccGroup1Desc;
	}

	public String getCcGroup2() {
		return ccGroup2;
	}

	public void setCcGroup2(String ccGroup2) {
		this.ccGroup2 = ccGroup2;
	}

	public String getCcGroup2Desc() {
		return ccGroup2Desc;
	}

	public void setCcGroup2Desc(String ccGroup2Desc) {
		this.ccGroup2Desc = ccGroup2Desc;
	}

	public String getCcNo() {
		return ccNo;
	}

	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}

	public String getCcDesc() {
		return ccDesc;
	}

	public void setCcDesc(String ccDesc) {
		this.ccDesc = ccDesc;
	}

	public String getCcOrgUnit() {
		return ccOrgUnit;
	}

	public void setCcOrgUnit(String ccOrgUnit) {
		this.ccOrgUnit = ccOrgUnit;
	}


}
