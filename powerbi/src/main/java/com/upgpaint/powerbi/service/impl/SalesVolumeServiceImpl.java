package com.upgpaint.powerbi.service.impl;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.upgpaint.powerbi.db.service.SalesVolumeDbService;
import com.upgpaint.powerbi.rest.domain.SalesVolumeRestList;
import com.upgpaint.powerbi.service.SalesVolumeService;

@Service("salesVolumeService")
public class SalesVolumeServiceImpl implements SalesVolumeService{
	private static Logger log = LoggerFactory.getLogger(SalesVolumeServiceImpl.class);
	@Value("${salesVolume.url}")
	private String Url;
	@Value("${salesVolume.username}")
	private String UserName;
	@Value("${salesVolume.password}")
	private String Password;

	private SalesVolumeDbService salesVolumeDbService;
	@Autowired
	public SalesVolumeServiceImpl(@Qualifier("salesVolumeDbService")SalesVolumeDbService salesVolumeDbService) {
		super();
		this.salesVolumeDbService = salesVolumeDbService;
	}

	@Override
	public void getSalesVolumeFromSAP() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();

		// Create the headers with Basic Auth
		HttpHeaders headers = new HttpHeaders();
		String auth = UserName + ":" + Password;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		// Create the HttpEntity with the headers
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Execute the request
		ResponseEntity<SalesVolumeRestList> response = restTemplate.exchange(Url, HttpMethod.GET, entity,
			SalesVolumeRestList.class);

		if (response.getStatusCode() != HttpStatus.OK) {
			log.error("Get sales volume HTTP Return Code = {}", response.getStatusCode().value());
		} else {
			SalesVolumeRestList salesVolumeRestList = response.getBody();
			if (salesVolumeRestList.getData() != null) {
				log.info("Number of sales volume to add = {}", salesVolumeRestList.getData());
			  salesVolumeDbService.createSalesVolumeDetail(salesVolumeRestList);
			} else {
				log.error("***  NULL ***");
			}
		}
	}
}