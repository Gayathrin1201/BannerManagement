package com.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.exception.ServiceException;
import com.project.model.Banner;
import com.project.model.Region;
import com.project.model.TargetAudience;
import com.project.model.UploadImage;
import com.project.repository.BannerRepository;
import com.project.repository.RegionRepository;

@Service
public class BannerService {
	
	@Autowired
	private BannerRepository bannerRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	
	public Banner saveBanner(Banner banner) {
	    try {
	        List<Region> regions = banner.getRegion();
	        if(regions != null && !regions.isEmpty()) {
	            List<Region> existingRegions = new ArrayList<>();
	            for(Region region : regions) {
	                if(region.getRegionId() > 0) {
	                    Optional<Region> existingRegion = regionRepository.findById(region.getRegionId());
	                    existingRegion.ifPresent(existingRegions::add);
	                }
	            }
	            banner.setRegion(existingRegions);
	        }
	        return bannerRepository.save(banner);
	    } catch (Exception e) {
	    	
				throw new ServiceException("601", "Enter proper details");
			}
	    }
	

	
	
	
	public List<Region> getRegionsByBannerId(int bannerId) {
	    try {
	        Banner banner = bannerRepository.findById(bannerId).orElseThrow(() -> new IllegalArgumentException("Banner not found with id " + bannerId));
	        if (!banner.isDeleteFag()) { 
	            List<Region> regions = banner.getRegion();
	            return regions;
	        } else {
	        
	            throw new ServiceException("603", "Cannot fetch regions for a deleted banner");
	        }
	    }catch (ServiceException e) {
	            throw e;
	    } catch (Exception e) {
	        throw new ServiceException("602", "Enter proper bannerId to fetch the region");
	    }
	}

	
	public List<Banner> getBannersByRegionId(int regionId) {
		try {
	    Region region = regionRepository.findById(regionId).orElseThrow(() -> new IllegalArgumentException("Region not found with id " + regionId));;
	    List<Banner> banners = region.getBanner();
	    return banners;
		}catch (Exception e) {
			throw new ServiceException("604", "Enter proper regionId to  fetch the banner");
		}
	}
	public Banner updateBanner(int bannerId,Banner banner) {
	    try {
	        Banner existingBanner = bannerRepository.findById(banner.getBannerId()).orElseThrow(() -> new IllegalArgumentException("Banner not found with id " + bannerId));
	        existingBanner.setBannerDescription(banner.getBannerDescription());
	        existingBanner.setBannerEndDate(banner.getBannerEndDate());
	        existingBanner.setBannerEndtime(banner.getBannerEndtime());
	        existingBanner.setBannerSize(banner.getBannerSize());
	        existingBanner.setBannerStartDate(banner.getBannerStartDate());
	        existingBanner.setBannerSubtitle(banner.getBannerSubtitle());
	        existingBanner.setBannerTitle(banner.getBannerTitle());
	        existingBanner.setBannerType(banner.getBannerType());
	        existingBanner.setC2Code(banner.getC2Code());
	        existingBanner.setCreatedBy(banner.getCreatedBy());
	        existingBanner.setCreatedTime(banner.getCreatedTime());
	        existingBanner.setCTAURL(banner.getCTAURL());
	        existingBanner.setDirectionofRotation(banner.getDirectionofRotation());
	        existingBanner.setEditedBy(banner.getEditedBy());
	        existingBanner.setEditedTime(banner.getEditedTime());
	        existingBanner.setFrequencyofRotation(banner.getFrequencyofRotation());
	        existingBanner.setMerchantID(banner.getMerchantID());
	        existingBanner.setTimerDuration(banner.getTimerDuration());
	        existingBanner.setTimerStartTime(banner.getTimerStartTime());
	        
	        List<UploadImage> uploadImages = banner.getUploadimage();
	        if(uploadImages != null && !uploadImages.isEmpty()) {
	            existingBanner.setUploadimage(uploadImages);
	        }
	        List<TargetAudience> targetAudience = banner.getTargetAudience();
	        if(targetAudience != null && !targetAudience.isEmpty()) {
	            existingBanner.setTargetAudience(targetAudience);
	        }
	        return bannerRepository.save(banner);
	    } catch (Exception e) {
			throw new ServiceException("605", "Enter proper bannerId to update the existing banner");
		}
	    }
	
	public void deleteBanner(int bannerId) {
	    try {
	        Banner banner = bannerRepository.findById(bannerId).orElseThrow(() -> new IllegalArgumentException("Banner not found with id " + bannerId));
	        banner.setDeleteFag(true);
	        if (banner != null) {
	        	 bannerRepository.save(banner);
	            bannerRepository.deleteBannerAssociationsWithRegions(bannerId);
	            //bannerRepository.delete(banner);
	           
	        }
	    } catch (Exception e) {
			throw new ServiceException("606", "Enter proper bannerId to delete the existing banner");
		}
	}
}
