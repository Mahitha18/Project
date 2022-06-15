package com.policy.management.consumer.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.management.consumer.models.PropertyMaster;


public interface PropertyMasterRepo extends JpaRepository<PropertyMaster, Long> {

	PropertyMaster findByPropertytypeAndInsurancetypeAndBuildingtype(String propertytype, String insurancetype,
			String buildingtype);

	
}
