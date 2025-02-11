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

import com.upgpaint.powerbi.db.service.MaterialDbService;

import com.upgpaint.powerbi.rest.domain.MaterialRestList;
import com.upgpaint.powerbi.service.MaterialService;


@Service("materialService")
public class MaterialServiceImpl implements MaterialService{
	private static Logger log = LoggerFactory.getLogger(MaterialServiceImpl.class);
	@Value("${material.url}")
	private String Url;
	@Value("${material.username}")
	private String UserName;
	@Value("${material.password}")
	private String Password;
	
	private MaterialDbService materialdbService;
    @Autowired
	public MaterialServiceImpl(@Qualifier("materialdbService") MaterialDbService materialdbService) {
		super();
		this.materialdbService = materialdbService;
	}
    
    @Override
	public void getMaterialFromSAP() {
		RestTemplate restTemplate = new RestTemplate();

		// Create the headers with Basic Auth
		HttpHeaders headers = new HttpHeaders();
		String auth = UserName + ":" + Password;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		// Create the HttpEntity with the headers
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<MaterialRestList> response = restTemplate.exchange(Url, HttpMethod.GET, entity,
				MaterialRestList.class);
            if(response.getStatusCode()!=HttpStatus.OK) {
            	log.error("Get Material From HTTP Return Code={}",response.getStatusCode().value());
            }else {
            	MaterialRestList materialRestList=response.getBody();
            	if(materialRestList.getData()!=null) {
            		log.info("Number of material to add={}",materialRestList.getData());
            		materialdbService.createMaterial(materialRestList);
            	}else {
            		log.error("NUll");
            	}
            }
}
}
