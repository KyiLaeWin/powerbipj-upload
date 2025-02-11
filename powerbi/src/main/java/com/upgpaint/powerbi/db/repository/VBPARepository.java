package com.upgpaint.powerbi.db.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.upgpaint.powerbi.db.entity.VBPAEntity;

import jakarta.transaction.Transactional;

@Repository("vbpaRepository")
public interface VBPARepository extends JpaRepository<VBPAEntity, Long>{
	@Query("SELECT v FROM VBPAEntity v WHERE v.billingDocument = :billingDocument AND v.partner=:partner")
    public VBPAEntity findVBPA(@Param("billingDocument")long billingDocument,@Param("partner")String partner);
     @Modifying
	 @Query("DELETE FROM VBPAEntity v WHERE v.billingDocument = :billingDocument ")
	 public void DeleteByBillDOc(@Param("billingDocument")Long billingDocument);

     @Query("SELECT v FROM VBPAEntity v WHERE v.billingDate >= :pastDate")
     List<VBPAEntity> findVBPAWithinLast60Days(@Param("pastDate") LocalDate pastDate);
	
}
