package com.policy.management.consumer.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.management.consumer.models.Consumer;

public interface ConsumerRepo extends JpaRepository<Consumer, Long> {

}
