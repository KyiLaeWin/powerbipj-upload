package com.upgpaint.powerbi.db.service;

import com.upgpaint.powerbi.db.entity.Salesvolume;
import com.upgpaint.powerbi.rest.domain.SalesVolumeRestList;

import jakarta.transaction.Transactional;
@Transactional
public interface SalesVolumeDbService extends AbstractDbServiceManager<Salesvolume, Long>{
	public void createSalesVolumeDetail(SalesVolumeRestList salesVolumeRestList);
	public void removeAllSalesVolumebyBillDov(Long billingDocument);
}