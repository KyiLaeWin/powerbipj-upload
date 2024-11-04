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
import com.upgpaint.powerbi.db.service.VBPADBService;
import com.upgpaint.powerbi.rest.domain.VBPARestList;
import com.upgpaint.powerbi.service.VBPAService;

@Service("vpaService")
public class VBPAServiceImpl implements VBPAService{
	private static Logger log = LoggerFactory.getLogger(VBPAServiceImpl.class);
	@Value("${vbpa.url}")
	private String Url;
	@Value("${vbpa.username}")
	private String UserName;
	@Value("${vbpa.password}")
	private String Password;
	
	private VBPADBService vpdbService;
    @Autowired
	public VBPAServiceImpl(@Qualifier("vbpadbService") VBPADBService vpdbService) {
		super();
		this.vpdbService = vpdbService;
	}
	@Override
	public void getVBPAFromSAP() {
		RestTemplate restTemplate = new RestTemplate();

		// Create the headers with Basic Auth
		HttpHeaders headers = new HttpHeaders();
		String auth = UserName + ":" + Password;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		// Create the HttpEntity with the headers
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<VBPARestList> response = restTemplate.exchange(Url, HttpMethod.GET, entity,
				VBPARestList.class);
            if(response.getStatusCode()!=HttpStatus.OK) {
            	log.error("Get VBPA From HTTP Return Code={}",response.getStatusCode().value());
            }else {
            	VBPARestList vbpaRestList=response.getBody();
            	if(vbpaRestList.getData()!=null) {
            		log.info("Number of vbpa to add={}",vbpaRestList.getData());
            		vpdbService.createVBPA(vbpaRestList);
            	}else {
            		log.error("NUll");
            	}
            }
}
}