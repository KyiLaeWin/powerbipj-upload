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
import com.upgpaint.powerbi.db.service.MMDBService;
import com.upgpaint.powerbi.rest.domain.MMRestList;
import com.upgpaint.powerbi.service.MMService;

@Service("mmService")
public class MMServiceImpl implements MMService{
	private static Logger log = LoggerFactory.getLogger(MMServiceImpl.class);
	@Value("${mm.url}")
	private String Url;
	@Value("${mm.username}")
	private String UserName;
	@Value("${mm.password}")
	private String Password;
	
	private MMDBService mmdbService;
    @Autowired
	public MMServiceImpl(@Qualifier("mmdbService") MMDBService mmdbService) {
		super();
		this.mmdbService = mmdbService;
	}
    
    @Override
	public void getMMFromSAP() {
		RestTemplate restTemplate = new RestTemplate();

		// Create the headers with Basic Auth
		HttpHeaders headers = new HttpHeaders();
		String auth = UserName + ":" + Password;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		// Create the HttpEntity with the headers
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<MMRestList> response = restTemplate.exchange(Url, HttpMethod.GET, entity,
				MMRestList.class);
            if(response.getStatusCode()!=HttpStatus.OK) {
            	log.error("Get MM From HTTP Return Code={}",response.getStatusCode().value());
            }else {
            	MMRestList mmRestList=response.getBody();
            	if(mmRestList.getData()!=null) {
            		log.info("Number of mm to add={}",mmRestList.getData());
            		mmdbService.createMM(mmRestList);
            	}else {
            		log.error("NUll");
            	}
            }
}
	
}
