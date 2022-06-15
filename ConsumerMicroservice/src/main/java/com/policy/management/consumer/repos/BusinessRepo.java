package com.policy.management.consumer.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.management.consumer.models.Business;


public interface BusinessRepo extends JpaRepository<Business, Long> {

	Business findByConsumerId(Long consumerId);

	boolean existsByConsumerId(Long consumerId);
}
