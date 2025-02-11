package com.upgpaint.powerbi.db.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.upgpaint.powerbi.db.entity.MMEntity;
import com.upgpaint.powerbi.db.repository.MMRepository;
import com.upgpaint.powerbi.db.service.MMDBService;
import com.upgpaint.powerbi.rest.domain.MMRest;
import com.upgpaint.powerbi.rest.domain.MMRestList;

import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.transaction.Transactional;

@Service("mmdbService")
@Transactional
public class MMDbServiceImpl extends AbstractDbServiceManagerImpl<MMEntity,Long> implements MMDBService{
	private static final Logger log = LoggerFactory.getLogger(MMDbServiceImpl.class);
    private final MMRepository mmRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    @Autowired
    public MMDbServiceImpl(@Qualifier("mmRepository") MMRepository mmRepository) {
        super(mmRepository);
        this.mmRepository = mmRepository;
    }

    @Override
    public void createMM(MMRestList mmRestList) {
        List<MMRest> mmRestData = mmRestList.getData().getResults();
       
        // Step 1: Collect incoming billing documents

        Set<String> incomingMaterials = mmRestData.stream()
                .map(MMRest::getMaterialNumber) // Directly map to the materialNumber as String
                .collect(Collectors.toSet());

	    // Step 2: Fetch existing sales volumes within the last 60 days
	    List<MMEntity> existingMMs = mmRepository.findAll();
	    
	    
	 // Step 3: Collect existing material numbers as Strings
	    Set<String> existingMaterialNos = existingMMs.stream()
	            .map(MMEntity::getMaterialNumber) // Directly map to materialNumber as String
	            .collect(Collectors.toSet());
	    
	    // Step 4: Insert new records from OData based on billing document
	    for (MMRest mmRestItem : mmRestData) {
	        String materialNumber = mmRestItem.getMaterialNumber();

	        // Only insert if the billing document is not already in the database
	        if (!existingMaterialNos.contains(materialNumber)) {
	            MMEntity mmEntity = new MMEntity();
	            mmEntity.setMaterialNumber(mmRestItem.getMaterialNumber());
	            mmEntity.setMaterialDesc(mmRestItem.getMaterialDesc());
	            mmEntity.setMaterialType(mmRestItem.getMaterialType());
	            mmEntity.setMaterialGp(mmRestItem.getMaterialGrp());
	            mmEntity.setBrand(mmRestItem.getBrand());
	            mmEntity.setbUOM(mmRestItem.getBUOM());
	        //    populateMM(newMM, mmRestItem);
	            this.save(mmEntity);
	            log.info("Added new MM details with Material Numnber: {}", materialNumber);
	        }
	    }
	    
	 // Step 5: Delete records from the database that are not in the incoming data
	    for (MMEntity existingMM : existingMMs) {
	        String materialNumber = existingMM.getMaterialNumber();

	        // Only delete if the billing document is not in the incoming data
	        if (!incomingMaterials.contains(materialNumber)) {
	            removeMM(materialNumber);
	            log.info("Deleted MM with Material Number: {}", materialNumber);
	        }
	    }
     
    }

     private void populateMM(MMEntity mmEntity, MMRest mmRestItem) {
        mmEntity.setMaterialNumber(mmRestItem.getMaterialNumber());
        mmEntity.setMaterialDesc(mmRestItem.getMaterialDesc());
        mmEntity.setMaterialType(mmRestItem.getMaterialType());
        mmEntity.setMaterialGp(mmRestItem.getMaterialGrp());
        mmEntity.setBrand(mmRestItem.getBrand());
        mmEntity.setbUOM(mmRestItem.getBUOM());
        
        
    }

    @Override
    public void removeMM(String materialNumber) {
        mmRepository.DeleteByMaterialNo(materialNumber);
    }
	
	
	
}
