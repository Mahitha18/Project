package com.policy.management.consumer.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.management.consumer.models.Property;


public interface PropertyRepo extends JpaRepository<com.policy.management.consumer.models.Property, Long> {
	
	Property findByConsumerId(Long consumerId); 
}
