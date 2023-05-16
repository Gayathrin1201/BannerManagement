package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.model.Banner;
import com.project.model.TargetAudience;

public interface BannerRepository extends JpaRepository<Banner, Integer>{
	 @Modifying
	    @Query(value = "DELETE FROM region_banner WHERE banner_id = :bannerId", nativeQuery = true)
	    void deleteBannerAssociationsWithRegions(@Param("bannerId") int bannerId);
}
