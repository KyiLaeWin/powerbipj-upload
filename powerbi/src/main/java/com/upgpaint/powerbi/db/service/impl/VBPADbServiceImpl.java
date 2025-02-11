package com.upgpaint.powerbi.db.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.upgpaint.powerbi.db.entity.VBPAEntity;
import com.upgpaint.powerbi.db.repository.VBPARepository;
import com.upgpaint.powerbi.db.service.VBPADBService;
import com.upgpaint.powerbi.rest.domain.VBPARest;
import com.upgpaint.powerbi.rest.domain.VBPARestList;
import java.util.stream.Collectors;
import java.util.Map;

import jakarta.transaction.Transactional;

@Service("vbpadbService")
@Transactional
public class VBPADbServiceImpl extends AbstractDbServiceManagerImpl<VBPAEntity,Long> implements VBPADBService{
    private static final Logger log = LoggerFactory.getLogger(VBPADbServiceImpl.class);
    private final VBPARepository vbpaRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    @Autowired
    public VBPADbServiceImpl(@Qualifier("vbpaRepository") VBPARepository vbpaRepository) {
        super(vbpaRepository);
        this.vbpaRepository = vbpaRepository;
    }

    @Override
    public void createVBPA(VBPARestList vbpaRestList) {
        List<VBPARest> vbpaRestData = vbpaRestList.getData().getResults();
        LocalDate now = LocalDate.now();
        LocalDate pastDate = now.minusDays(60);

        // Step 1: Collect incoming billing documents

        Set<Long> incomingBillingDocuments = vbpaRestData.stream()
    	        .map(vbpaRestItem -> Long.parseLong(vbpaRestItem.getBillingDocument()))
    	        .collect(Collectors.toSet());
        

	    // Step 2: Fetch existing sales volumes within the last 60 days
	    List<VBPAEntity> existingVBPAs = vbpaRepository.findVBPAWithinLast60Days(pastDate);
	    
	    
	    // Step 3: Collect existing billing documents only
	    Set<Long> existingBillingDocuments = existingVBPAs.stream()
	        .map(VBPAEntity::getBillingDocument)
	        .collect(Collectors.toSet());
	    
	    // Step 4: Insert new records from OData based on billing document
	    for (VBPARest vbpaRestItem : vbpaRestData) {
	        Long billingDocument = Long.parseLong(vbpaRestItem.getBillingDocument());

	        // Only insert if the billing document is not already in the database
	        if (!existingBillingDocuments.contains(billingDocument)) {
	            VBPAEntity newVBPA = new VBPAEntity();
	            populateVBPA(newVBPA, vbpaRestItem);
	            this.save(newVBPA);
	            log.info("Added new VBPA details with Billing document: {}", billingDocument);
	        }
	    }
	    
	 // Step 5: Delete records from the database that are not in the incoming data
	    for (VBPAEntity existingVBPA : existingVBPAs) {
	        Long billingDocument = existingVBPA.getBillingDocument();

	        // Only delete if the billing document is not in the incoming data
	        if (!incomingBillingDocuments.contains(billingDocument)) {
	            removeVBPA(billingDocument);
	            log.info("Deleted VBPA with Billing document: {}", billingDocument);
	        }
	    }
     
    }

     private void populateVBPA(VBPAEntity vbpaEntity, VBPARest vbpaRestItem) {
        vbpaEntity.setBillingDocument(Long.parseLong(vbpaRestItem.getBillingDocument()));
        vbpaEntity.setBillingDate(vbpaRestItem.getBillingDate());
        vbpaEntity.setPartner(vbpaRestItem.getPartner());
        vbpaEntity.setBusinessPartner(vbpaRestItem.getBusinessPartner());
    }

    @Override
    public void removeVBPA(long billingDocument) {
        vbpaRepository.DeleteByBillDOc(billingDocument);
    }
}
