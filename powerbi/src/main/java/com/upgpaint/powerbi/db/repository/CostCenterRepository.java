package com.upgpaint.powerbi.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upgpaint.powerbi.db.entity.CostCenter;

@Repository("costCenterRepository")
public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {

	@Query("SELECT c FROM CostCenter c WHERE c.CC_No = :ccNo")
    public CostCenter findCostCenterByCCNum(@Param("ccNo")Long ccNo);


}
