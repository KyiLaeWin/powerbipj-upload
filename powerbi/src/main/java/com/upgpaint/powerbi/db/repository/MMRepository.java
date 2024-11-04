package com.upgpaint.powerbi.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.upgpaint.powerbi.db.entity.MMEntity;

@Repository("mmRepository")
public interface MMRepository extends JpaRepository<MMEntity, Long>{
	@Modifying
	 @Query("DELETE FROM MMEntity m WHERE m.materialNumber = :MaterialNumber ")
	 public void DeleteByMaterialNo(@Param("MaterialNumber")String materialNumber);
		
}
