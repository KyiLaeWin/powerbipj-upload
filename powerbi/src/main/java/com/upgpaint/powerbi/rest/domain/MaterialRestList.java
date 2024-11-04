package com.upgpaint.powerbi.rest.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MaterialRestList {
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
		private List<MaterialRest> results;

		public List<MaterialRest> getResults() {
			return results;
		}

		public void setResults(List<MaterialRest> results) {
			this.results = results;
		}
	}
}
