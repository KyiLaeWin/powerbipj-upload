package com.upgpaint.powerbi.db.service;
import com.upgpaint.powerbi.db.entity.VBPAEntity;
import com.upgpaint.powerbi.rest.domain.VBPARestList;
import jakarta.transaction.Transactional;
@Transactional
public interface  VBPADBService extends AbstractDbServiceManager<VBPAEntity, Long>{
  public void createVBPA(VBPARestList vbpaRestList );
  public void removeVBPA(long billingDocument);
}
