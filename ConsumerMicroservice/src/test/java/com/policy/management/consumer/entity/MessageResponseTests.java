package com.policy.management.consumer.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.policy.management.consumer.responses.MessageResponse;



public class MessageResponseTests {
	
	MessageResponse messageResponse;
	
	@Test
    public void testConsumerBusinessDetails() throws Exception {
		messageResponse=new MessageResponse();
		messageResponse.setMessage("test");
		assertEquals("test",messageResponse.getMessage());
	}
	
	@Test
    public void testConsumerBusinessDetailsConstructor() throws Exception {
		messageResponse=new MessageResponse("test");
		assertEquals("test",messageResponse.getMessage());
	}
}
