package com.upgpaint.powerbi.rest.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CostCenterRestList {

	@JsonProperty("d")
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public static class Data {
		@JsonProperty("results")
		private List<CostCenterRest> results;

		public List<CostCenterRest> getResults() {
			return results;
		}

		public void setResults(List<CostCenterRest> results) {
			this.results = results;
		}
	}

}