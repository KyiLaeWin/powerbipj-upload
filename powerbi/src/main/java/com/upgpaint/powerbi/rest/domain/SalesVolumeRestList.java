package com.upgpaint.powerbi.rest.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesVolumeRestList {
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
		private List<SalesVolumeRest> results;

		public List<SalesVolumeRest> getResults() {
			return results;
		}

		public void setResults(List<SalesVolumeRest> results) {
			this.results = results;
		}
	}
}
