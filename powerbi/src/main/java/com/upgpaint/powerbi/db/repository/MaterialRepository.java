package com.upgpaint.powerbi.db.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.upgpaint.powerbi.db.entity.Material;

import jakarta.transaction.Transactional;

@Repository("materialRepository")
public interface MaterialRepository extends JpaRepository<Material, Long>{
	 @Modifying
	 @Query("DELETE FROM Material m WHERE m.materialNumber = :MaterialNumber ")
	 public void DeleteByMaterialNo(@Param("MaterialNumber")String materialNumber);
}
