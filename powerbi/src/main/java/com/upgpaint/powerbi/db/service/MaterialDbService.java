package com.upgpaint.powerbi.db.service;
import com.upgpaint.powerbi.db.entity.Material;
import com.upgpaint.powerbi.rest.domain.MMRestList;
import com.upgpaint.powerbi.rest.domain.MaterialRestList;
import jakarta.transaction.Transactional;
@Transactional
public interface MaterialDbService extends AbstractDbServiceManager<Material, Long>{
	public void createMaterial(MaterialRestList materialRestList );
	  public void removeMaterial(String MaterialNumber);

}
