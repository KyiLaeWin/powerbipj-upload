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


import com.upgpaint.powerbi.db.entity.Material;

import com.upgpaint.powerbi.db.repository.MaterialRepository;

import com.upgpaint.powerbi.db.service.MaterialDbService;

import com.upgpaint.powerbi.rest.domain.MaterialRest;
import com.upgpaint.powerbi.rest.domain.MaterialRestList;
import java.util.stream.Collectors;
import java.util.Map;

import jakarta.transaction.Transactional;

@Service("materialdbService")
@Transactional
public class MaterialDbServiceImpl extends AbstractDbServiceManagerImpl<Material,Long> implements MaterialDbService{
	private static final Logger log = LoggerFactory.getLogger(MaterialDbServiceImpl.class);
    private final MaterialRepository materialRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    @Autowired
    public MaterialDbServiceImpl(@Qualifier("materialRepository") MaterialRepository materialRepository) {
        super(materialRepository);
        this.materialRepository = materialRepository;
    }

    @Override
    public void createMaterial(MaterialRestList materialRestList) {
        List<MaterialRest> materialRestData = materialRestList.getData().getResults();
       
        // Step 1: Collect incoming billing documents

        Set<String> incomingMaterials = materialRestData.stream()
                .map(MaterialRest::getMaterialNumber) // Directly map to the materialNumber as String
                .collect(Collectors.toSet());

	    // Step 2: Fetch existing sales volumes within the last 60 days
	    List<Material> existingMMs = materialRepository.findAll();
	    
	    
	 // Step 3: Collect existing material numbers as Strings
	    Set<String> existingMaterialNos = existingMMs.stream()
	            .map(Material::getMaterialNumber) // Directly map to materialNumber as String
	            .collect(Collectors.toSet());
	    
	    // Step 4: Insert new records from OData based on billing document
	    for (MaterialRest materialRestItem : materialRestData) {
	        String materialNumber = materialRestItem.getMaterialNumber();

	        // Only insert if the billing document is not already in the database
	        if (!existingMaterialNos.contains(materialNumber)) {
	            Material newMM = new Material();
	            populateMaterial(newMM, materialRestItem);
	            this.save(newMM);
	            log.info("Added new Material details with Material Numnber: {}", materialNumber);
	        }
	    }
	    
	 // Step 5: Delete records from the database that are not in the incoming data
	    for (Material existingMM : existingMMs) {
	        String materialNumber = existingMM.getMaterialNumber();

	        // Only delete if the billing document is not in the incoming data
	        if (!incomingMaterials.contains(materialNumber)) {
	            removeMaterial(materialNumber);
	            log.info("Deleted MM with Material Number: {}", materialNumber);
	        }
	    }
     
    }

     private void populateMaterial(Material mmEntity, MaterialRest mmRestItem) {
        mmEntity.setMaterialNumber(mmRestItem.getMaterialNumber());
        mmEntity.setMaterialDesc(mmRestItem.getMaterialDesc());
        mmEntity.setMaterialType(mmRestItem.getMaterialType());
        mmEntity.setMaterialGp(mmRestItem.getMaterialGrp());
        mmEntity.setBrand(mmRestItem.getBrand());
        mmEntity.setbUOM(mmRestItem.getBUOM());
        
        
    }

    @Override
    public void removeMaterial(String materialNumber) {
        materialRepository.DeleteByMaterialNo(materialNumber);
    }
	
	
}
