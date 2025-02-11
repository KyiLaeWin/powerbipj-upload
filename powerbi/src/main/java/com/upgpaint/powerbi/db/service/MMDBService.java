package com.upgpaint.powerbi.db.service;

import com.upgpaint.powerbi.db.entity.MMEntity;
import com.upgpaint.powerbi.rest.domain.MMRestList;
import jakarta.transaction.Transactional;

@Transactional
public interface MMDBService extends AbstractDbServiceManager<MMEntity, Long> {
	public void createMM(MMRestList mmRestList);

	public void removeMM(String MaterialNumber);
}
