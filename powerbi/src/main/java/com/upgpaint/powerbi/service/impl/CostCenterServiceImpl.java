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

import com.upgpaint.powerbi.db.service.CostCenterDbService;
import com.upgpaint.powerbi.rest.domain.CostCenterRestList;
import com.upgpaint.powerbi.service.CostCenterService;

@Service("costCenterService")
public class CostCenterServiceImpl implements CostCenterService {

	private static Logger log = LoggerFactory.getLogger(CostCenterServiceImpl.class);

	@Value("${costcenter.url}")
	private String costcenterUrl;

	@Value("${costcenter.username}")
	private String username;

	@Value("${costcenter.password}")
	private String password;

	private CostCenterDbService costCenterDbservice;

	@Autowired
	public CostCenterServiceImpl(@Qualifier("costCenterDbService") CostCenterDbService costCenterDbservice) {
		super();
		this.costCenterDbservice = costCenterDbservice;
	}

	@Override
	public void getCostCenterFromSAP() {
		RestTemplate restTemplate = new RestTemplate();

		// Create the headers with Basic Auth
		HttpHeaders headers = new HttpHeaders();
		String auth = username + ":" + password;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		// Create the HttpEntity with the headers
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Execute the request
		ResponseEntity<CostCenterRestList> response = restTemplate.exchange(costcenterUrl, HttpMethod.GET, entity,
				CostCenterRestList.class);

		if (response.getStatusCode() != HttpStatus.OK) {
			log.error("Get Cost Center HTTP Return Code = {}", response.getStatusCode().value());
		} else {
			CostCenterRestList costCenterRestList = response.getBody();
			if (costCenterRestList.getData() != null) {
				log.info("Number of CostCenter to add = {}", costCenterRestList.getData());
				costCenterDbservice.createCostCenterDetail(costCenterRestList);
			} else {
				log.error("*** costCenterRestList.getNumofRec() is NULL ***");
			}
		}
	}
}
