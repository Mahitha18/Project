package com.policy.management.consumer.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.management.consumer.models.BusinessMaster;

public interface BusinessMasterRepo extends JpaRepository<BusinessMaster, Long> {

	BusinessMaster findByBusinesscategoryAndBusinesstype(String businesscategory, String businesstype);
	
	
}



