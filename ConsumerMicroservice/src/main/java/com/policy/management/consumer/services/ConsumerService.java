package com.policy.management.consumer.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.policy.management.consumer.models.Consumer;
import com.policy.management.consumer.models.Property;
import com.policy.management.consumer.requests.BusinessPropertyRequest;
import com.policy.management.consumer.requests.ConsumerBusinessRequest;
import com.policy.management.consumer.responses.BusinessPropertyDetails;
import com.policy.management.consumer.responses.ConsumerBusinessDetails;
import com.policy.management.consumer.responses.MessageResponse;

@Service
public interface ConsumerService {

	public MessageResponse createConsumerBusiness(ConsumerBusinessRequest consumerBusinessRequest);

	long calBusinessValue(Long businessturnover, Long capitalinvested);

	ConsumerBusinessDetails viewConsumerBusiness(Long consumerid);

	MessageResponse updateConsumerBusiness(ConsumerBusinessDetails consumerBusinessDetails);

	Long calPropertyValue(Long costoftheasset, Long salvagevalue, Long usefullifeoftheAsset);
	
	MessageResponse createBusinessProperty(BusinessPropertyRequest bpr);
	
	MessageResponse updateBusinessProperty(BusinessPropertyDetails bpd);
	
	Property viewConsumerProperty(long consumerId,Long propertyid);//changed
	
	public List<Consumer> view();

	Boolean checkPropertyEligibility(String propertytype, String insurancetype, String buildingtype, Long buildingage);

	Boolean checkBusinessEligibility(ConsumerBusinessRequest consumerBusinessRequest);
			

}
