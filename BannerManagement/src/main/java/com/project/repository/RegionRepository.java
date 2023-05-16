package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Region;
import com.project.model.TargetAudience;

public interface RegionRepository extends JpaRepository<Region, Integer>{

}
