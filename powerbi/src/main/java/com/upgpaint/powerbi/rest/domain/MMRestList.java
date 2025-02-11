package com.upgpaint.powerbi.rest.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class MMRestList {
	
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
		private List<MMRest> results;

		public List<MMRest> getResults() {
			return results;
		}

		public void setResults(List<MMRest> results) {
			this.results = results;
		}
	}

}
