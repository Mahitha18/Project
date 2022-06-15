package com.policy.management.consumer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import com.policy.management.consumer.services.ConsumerServiceImpl;


@ExtendWith(MockitoExtension.class)
public class ConsumerServiceTests {
	
	@InjectMocks
	private ConsumerServiceImpl consumerService;
	
	
	@Test
    public void testCalculateBusinessValue() throws Exception {
		long res=consumerService.calBusinessValue((long)1,(long)2);
		assertEquals((long)10, res);//20
    }
	
	@Test
    public void testCalculatePropertyValue() throws Exception {
		long res=consumerService.calPropertyValue((long)200,(long)20,(long)13);
		assertEquals((long)9, res);
    }
	
	
	

}
