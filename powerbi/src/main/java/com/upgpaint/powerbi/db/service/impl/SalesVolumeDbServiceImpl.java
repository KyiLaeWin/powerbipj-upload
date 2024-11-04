package com.upgpaint.powerbi.db.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upgpaint.powerbi.db.entity.Salesvolume;
import com.upgpaint.powerbi.db.repository.SalesVolumeRepository;
import com.upgpaint.powerbi.db.service.SalesVolumeDbService;
import com.upgpaint.powerbi.rest.domain.SalesVolumeRest;
import com.upgpaint.powerbi.rest.domain.SalesVolumeRestList;
import java.util.stream.Collectors;
import java.util.Map;


@Service("salesVolumeDbService")
@Transactional
public class SalesVolumeDbServiceImpl extends AbstractDbServiceManagerImpl<Salesvolume, Long> implements SalesVolumeDbService{
private static Logger log = LoggerFactory.getLogger(SalesVolumeDbServiceImpl.class);

   private SalesVolumeRepository salesVolumeRepository;
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
   
   @Autowired
   public SalesVolumeDbServiceImpl(@Qualifier("salesVolumeRepository") SalesVolumeRepository salesVolumeRepository) {
        super(salesVolumeRepository);
      this.salesVolumeRepository = salesVolumeRepository;
   }

   @Override
   public void createSalesVolumeDetail(SalesVolumeRestList salesVolumeRestList) {
	   List<SalesVolumeRest> salesVolumeRest = salesVolumeRestList.getData().getResults();
	    LocalDate now = LocalDate.now();
	    LocalDate pastDate = now.minusDays(60);

	    // Step 1: Collect incoming billing documents
	    Set<Long> incomingBillingDocuments = salesVolumeRest.stream()
	        .map(salesVolumeRestItem -> Long.parseLong(salesVolumeRestItem.getBill_Document()))
	        .collect(Collectors.toSet());

	    // Step 2: Fetch existing sales volumes within the last 60 days
	    List<Salesvolume> existingSalesVolumes = salesVolumeRepository.findSalesVolumesWithinLast60Days(pastDate);
	    
	 // Step 3: Create a set of existing billing documents for quick lookup
	    Set<Long> existingBillingDocuments = existingSalesVolumes.stream()
	        .map(Salesvolume::getBillingDocument)
	        .collect(Collectors.toSet());
	    
	    
	    // Step 4: Insert new records from OData
	    for (SalesVolumeRest salesVolumeRestItem : salesVolumeRest) {
	        Long billingDocument = Long.parseLong(salesVolumeRestItem.getBill_Document());
	        
	        // Only insert if the billing document is not already in the database
	        if (!existingBillingDocuments.contains(billingDocument)) {
	            Salesvolume newSalesVolume = new Salesvolume();
	            populateSalesVolume(newSalesVolume, salesVolumeRestItem);
	            this.save(newSalesVolume);
	            log.info("Added new sales volume details with Billing document: {}", billingDocument);
	        }
	    
	    }
	    
	    // Step 5: Delete records not present in incoming data
	    for (Salesvolume existingSalesVolume : existingSalesVolumes) {
	        Long billingDocument = existingSalesVolume.getBillingDocument();
	        
	        // Only delete if the billing document is not in the incoming data
	        if (!incomingBillingDocuments.contains(billingDocument)) {
	            removeAllSalesVolumebyBillDov(billingDocument);
	            log.info("Deleted sales volume with Billing document: {}", billingDocument);
	        }
	        }
   }

  private void populateSalesVolume(Salesvolume salesVolume, SalesVolumeRest salesVolumeRestItem) {
       salesVolume.setBillingDocument(Long.parseLong(salesVolumeRestItem.getBill_Document()));
       salesVolume.setBillingCategory(salesVolumeRestItem.getBilling_Category());
       salesVolume.setSalesGroup(salesVolumeRestItem.getSales_Group());
       salesVolume.setSalesOffice(salesVolumeRestItem.getSales_Office());
       salesVolume.setBillingDate(salesVolumeRestItem.getBillingDate());
       salesVolume.setRegion(salesVolumeRestItem.getRegion());
       salesVolume.setDistChannel(salesVolumeRestItem.getDist_Channel());
       salesVolume.setSalesOrg(salesVolumeRestItem.getSale_org());
       salesVolume.setVolume(salesVolumeRestItem.getVolume());
       salesVolume.setVolumeUnit(salesVolumeRestItem.getV_unit());
       salesVolume.setMaterialNo(salesVolumeRestItem.getMaterial());
       salesVolume.setPlant(salesVolumeRestItem.getPlant());
       salesVolume.setStorageLoc(salesVolumeRestItem.getStorage_loc());
       salesVolume.setNet_Value(salesVolumeRestItem.getNet_Value());
       salesVolume.setRebateBasis(salesVolumeRestItem.getRebate_Basis());
       salesVolume.setOutputTax(salesVolumeRestItem.getOutput_tax());
       salesVolume.setCsd_point(salesVolumeRestItem.getCsdPoint());
   }

   
	@Override
	public void removeAllSalesVolumebyBillDov(Long billingDocument) {
		// TODO Auto-generated method stub
		salesVolumeRepository.RemoveSalesVolumeByBillDOc(billingDocument);
	}
}