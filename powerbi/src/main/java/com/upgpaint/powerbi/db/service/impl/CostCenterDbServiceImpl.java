package com.upgpaint.powerbi.db.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upgpaint.powerbi.db.entity.CostCenter;
import com.upgpaint.powerbi.db.repository.CostCenterRepository;
import com.upgpaint.powerbi.db.service.CostCenterDbService;
import com.upgpaint.powerbi.rest.domain.CostCenterRest;
import com.upgpaint.powerbi.rest.domain.CostCenterRestList;

@Service("costCenterDbService")
@Transactional
public class CostCenterDbServiceImpl extends AbstractDbServiceManagerImpl<CostCenter, Long> implements CostCenterDbService{
	private static Logger log = LoggerFactory.getLogger(CostCenterDbServiceImpl.class);
	private CostCenterRepository costCenterRepository;

    @Autowired
    public CostCenterDbServiceImpl(@Qualifier("costCenterRepository") CostCenterRepository costCenterRepository) {
        super(costCenterRepository);
        this.costCenterRepository = costCenterRepository;
    }

	@Override
	public void createCostCenterDetail(CostCenterRestList costCenterRestList) {

		List<CostCenterRest> costCenterRests = costCenterRestList.getData().getResults();
		for (CostCenterRest CostCenterRest : costCenterRests) {
			try {

				CostCenter costCenterFromDb = costCenterRepository.findCostCenterByCCNum(Long.parseLong(CostCenterRest.getCcNo()));

				if (costCenterFromDb != null){
					costCenterFromDb.setCC_Group1(CostCenterRest.getCcGroup1());
				    costCenterFromDb.setCC_Group1_Desc(CostCenterRest.getCcGroup1Desc());
					costCenterFromDb.setCC_Group2(CostCenterRest.getCcGroup2());
					costCenterFromDb.setCC_Group2_Desc(CostCenterRest.getCcGroup2Desc());
					costCenterFromDb.setCC_Desc(CostCenterRest.getCcDesc());
					costCenterFromDb.setCC_OrgUnit(CostCenterRest.getCcOrgUnit());
					this.update(costCenterFromDb);
					log.info("Updated Cost Center detail with CC_No={}", CostCenterRest.getCcNo());
				} else {
					// Handle the case where no result is found
					CostCenter costCenter = new CostCenter();
					costCenter.setCC_Group1(CostCenterRest.getCcGroup1());
					costCenter.setCC_Group1_Desc(CostCenterRest.getCcGroup1Desc());
                    costCenter.setCC_Group2(CostCenterRest.getCcGroup2());
                    costCenter.setCC_Group2_Desc(CostCenterRest.getCcGroup2Desc());
					costCenter.setCC_No(Long.parseLong(CostCenterRest.getCcNo()));
					costCenter.setCC_OrgUnit(CostCenterRest.getCcOrgUnit());
					this.save(costCenter);

				}
			} catch (Exception e) {
				log.error("Exception in Cost CenterNumber  = {}");
				log.error("Exception ...", e);
			}

		}

	}

}
