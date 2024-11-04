package com.upgpaint.powerbi.db.service;

import com.upgpaint.powerbi.db.entity.CostCenter;
import com.upgpaint.powerbi.rest.domain.CostCenterRestList;

public interface CostCenterDbService extends AbstractDbServiceManager<CostCenter, Long> {
	public void createCostCenterDetail(CostCenterRestList costCenterRestList);
}