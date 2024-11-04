package com.upgpaint.powerbi.db.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upgpaint.powerbi.db.entity.Salesvolume;
@Repository("salesVolumeRepository")
public interface SalesVolumeRepository extends JpaRepository<Salesvolume, Long>{


@Modifying
 @Query("DELETE FROM Salesvolume s WHERE s.billingDocument = :billingDocument ")
   public void RemoveSalesVolumeByBillDOc(@Param("billingDocument")Long billingDocument);

@Query("SELECT s FROM Salesvolume s WHERE s.billingDocument = :billingDocument AND s.materialNo=:materialNo ")
public Salesvolume findSalesVolume(@Param("billingDocument")long billingDocument,@Param ("materialNo") String materialNo);

@Query("SELECT COUNT(s) FROM Salesvolume s")
long countSalesVolume();


@Query("SELECT s FROM Salesvolume s WHERE s.billingDocument = :billingDocument")
public List<Salesvolume> findSalesVolumeAll(@Param("billingDocument")List <Long> incomingBillingDocuments);

@Query("SELECT sv FROM Salesvolume sv WHERE sv.billingDate >= :pastDate")
List<Salesvolume> findSalesVolumesWithinLast60Days(@Param("pastDate") LocalDate pastDate);

}