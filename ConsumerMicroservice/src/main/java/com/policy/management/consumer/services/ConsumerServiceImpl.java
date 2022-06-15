package com.policy.management.consumer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.management.consumer.models.Business;
import com.policy.management.consumer.models.BusinessMaster;
import com.policy.management.consumer.models.Consumer;
import com.policy.management.consumer.models.Property;
import com.policy.management.consumer.models.PropertyMaster;
import com.policy.management.consumer.repos.BusinessMasterRepo;
import com.policy.management.consumer.repos.BusinessRepo;
import com.policy.management.consumer.repos.ConsumerRepo;
import com.policy.management.consumer.repos.PropertyMasterRepo;
import com.policy.management.consumer.repos.PropertyRepo;
import com.policy.management.consumer.requests.BusinessPropertyRequest;
import com.policy.management.consumer.requests.ConsumerBusinessRequest;
import com.policy.management.consumer.responses.BusinessPropertyDetails;
import com.policy.management.consumer.responses.ConsumerBusinessDetails;
import com.policy.management.consumer.responses.MessageResponse;


@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private ConsumerRepo consumerRepo;
	
	@Autowired
	private BusinessRepo businessRepo; 
	
	@Autowired
	private PropertyRepo propertyRepo;
	
	@Autowired
	private BusinessMasterRepo businessMasterRepo;
	
	@Autowired
	private PropertyMasterRepo propertyMasterRepo;
	
	@Override
	public MessageResponse createConsumerBusiness(ConsumerBusinessRequest consumerBusinessRequest) {
		
		Consumer consumer = new Consumer(consumerBusinessRequest.getFirstname(), consumerBusinessRequest.getLastname(),
				consumerBusinessRequest.getDob(), consumerBusinessRequest.getBusinessname(),
				consumerBusinessRequest.getPandetails(), consumerBusinessRequest.getEmail(),
				consumerBusinessRequest.getPhone(), consumerBusinessRequest.getWebsite(),
				consumerBusinessRequest.getBusinessoverview(), consumerBusinessRequest.getValidity(),
				consumerBusinessRequest.getAgentname(), consumerBusinessRequest.getAgentid());
		
		Consumer c = consumerRepo.save(consumer);
		long businessvalue = calBusinessValue(consumerBusinessRequest.getBusinessturnover(),
				consumerBusinessRequest.getCapitalinvested());
		Business business = new Business(c.getId(), consumerBusinessRequest.getBusinesscategory(),
				consumerBusinessRequest.getBusinesstype(), consumerBusinessRequest.getBusinessturnover(),
				consumerBusinessRequest.getCapitalinvested(), consumerBusinessRequest.getTotalemployees(),
				businessvalue, consumerBusinessRequest.getBusinessage());
		businessRepo.save(business);
		return new MessageResponse("Successfully created Consumer with Consumer ID: " + consumer.getId()
		+ "and Business ID: " + business.getId() + " . Thank you!");
	}
	
	
	public long calBusinessValue(Long businessturnover,Long capitalinvested) {
		
		if (businessturnover == 0 || capitalinvested == 0 || (businessturnover == capitalinvested)) {
			throw new NullPointerException("NullPointerException in CalBusinessValue");
		}
		
		double ratio = (double) businessturnover/capitalinvested;
		double sat = (ratio - capitalinvested)/(businessturnover - capitalinvested);
		
		return (long) sat*10;
	}
	
	
	public List<Consumer> view() {
		return consumerRepo.findAll();
	}


	@Override
	public ConsumerBusinessDetails viewConsumerBusiness(Long consumerid) {

		Consumer consumers = consumerRepo.getById(consumerid);
		Business business = businessRepo.findByConsumerId(consumerid);
		ConsumerBusinessDetails consumerBusinessDetails = new ConsumerBusinessDetails(consumers.getFirstname(),
				consumers.getLastname(), consumers.getDob(), consumers.getBusinessname(), consumers.getPandetails(),
				consumers.getEmail(), consumers.getPhone(), consumers.getWebsite(), consumers.getBusinessoverview(),
				consumers.getValidity(), consumers.getAgentname(), consumers.getAgentid(), business.getId(),
				business.getConsumerId(), business.getBusinesscategory(), business.getBusinesstype(),
				business.getBusinessturnover(), business.getCapitalinvested(), business.getTotalemployees(),
				business.getBusinessvalue(), business.getBusinessage()

		);
		return consumerBusinessDetails;
	}


	@Override
	public MessageResponse updateConsumerBusiness(ConsumerBusinessDetails consumerBusinessDetails) {
		
		Consumer consumers = consumerRepo.getById((Long)consumerBusinessDetails.getConsumerId());
		Business business = businessRepo.findByConsumerId(consumerBusinessDetails.getConsumerId());
		consumers.setFirstname(consumerBusinessDetails.getFirstname());
		consumers.setLastname(consumerBusinessDetails.getLastname());
		consumers.setDob(consumerBusinessDetails.getDob());
		consumers.setBusinessname(consumerBusinessDetails.getBusinessname());
		consumers.setPandetails(consumerBusinessDetails.getPandetails());
		consumers.setEmail(consumerBusinessDetails.getEmail());
		consumers.setPhone(consumerBusinessDetails.getPhone());
		consumers.setWebsite(consumerBusinessDetails.getWebsite());
		consumers.setBusinessname(consumerBusinessDetails.getBusinessname());
		consumers.setBusinessoverview(consumerBusinessDetails.getBusinessoverview());
		consumers.setValidity(consumerBusinessDetails.getValidity());
		consumers.setAgentname(consumerBusinessDetails.getAgentname());
		consumers.setAgentid(consumerBusinessDetails.getAgentid());

		Consumer consumersave = consumerRepo.save(consumers);
		business.setBusinesscategory(consumerBusinessDetails.getBusinesscategory());
		business.setBusinesstype(consumerBusinessDetails.getBusinesscategory());
		business.setBusinessturnover(consumerBusinessDetails.getBusinessturnover());
		business.setCapitalinvested(consumerBusinessDetails.getCapitalinvested());
		business.setTotalemployees(consumerBusinessDetails.getTotalemployees());
		business.setBusinessvalue(calBusinessValue(business.getBusinessturnover(), business.getCapitalinvested()));
		business.setBusinessage(consumerBusinessDetails.getBusinessage());

		Business businesssave = businessRepo.save(business);
		return new MessageResponse("Successfully Updated Consumer with Consumer ID: " + consumersave.getId()
				+ "and Business ID: " + businesssave.getId() + " . Thank you!");
	}
	
	@Override
	public Long calPropertyValue(Long costoftheasset, Long salvagevalue, Long usefullifeoftheAsset) {
		if (usefullifeoftheAsset == 0 || salvagevalue == 0 || costoftheasset == 0 || (costoftheasset == salvagevalue)) {
			throw new NullPointerException("NullPointerException in calPropertyValue");
		}
		Double x_ratio = (double) ((costoftheasset - salvagevalue) / usefullifeoftheAsset);
		Double x_max = (double) (costoftheasset / usefullifeoftheAsset);
		Double x_min = (double) (salvagevalue / usefullifeoftheAsset);
		Double range_diff = (double) 10;
		Double sat = ((x_ratio - x_min) / (x_max - x_min));
		Double propertyvalue = range_diff * sat;
		return (long) Math.abs(Math.round(propertyvalue));
	}
	


	@Override
	public MessageResponse createBusinessProperty(BusinessPropertyRequest bpr) {
		
		Long propertyValue = calPropertyValue(bpr.getCostoftheasset(),
				bpr.getSalvagevalue(), bpr.getUsefullifeoftheAsset());
		
		Property property = new Property(bpr.getBusinessId(),
				bpr.getConsumerId(), bpr.getInsurancetype(),
				bpr.getPropertytype(), bpr.getBuildingsqft(),
				bpr.getBuildingtype(), bpr.getBuildingstoreys(),
				bpr.getBuildingage(), propertyValue, bpr.getCostoftheasset(),
				bpr.getSalvagevalue(), bpr.getUsefullifeoftheAsset());
		Property propertysave = propertyRepo.save(property);
		
		return new MessageResponse(
				"SuccessFully Created Business Property with Property Id: " + propertysave.getId() + " . Thank you!!");
		
	}
	
	
	@Override
	public MessageResponse updateBusinessProperty(BusinessPropertyDetails bpd) {

		Property property = propertyRepo.findByConsumerId(bpd.getConsumerId());
		
		Long propertyValue = calPropertyValue(bpd.getCostoftheasset(),
				bpd.getSalvagevalue(), bpd.getUsefullifeoftheAsset());
		
		property.setInsurancetype(bpd.getInsurancetype());
		property.setPropertytype(bpd.getPropertytype());
		property.setBuildingsqft(bpd.getBuildingsqft());
		property.setBuildingtype(bpd.getBuildingtype());
		property.setBuildingstoreys(bpd.getBuildingstoreys());
		property.setBuildingage(bpd.getBuildingage());
		property.setPropertyvalue(propertyValue);
		property.setCostoftheasset(bpd.getCostoftheasset());
		property.setSalvagevalue(bpd.getSalvagevalue());
		property.setUsefullifeoftheAsset(bpd.getUsefullifeoftheAsset());

		Property propertysave = propertyRepo.save(property);
		return new MessageResponse(
				"SuccessFully Updated Business Property with Property Id :" + propertysave.getId() + " . Thank you!");

	}


//	@Override
//	public Property viewBusinessProperty(long consumerId) {
//
//		Property property = propertyRepo.findByConsumerId(consumerId);
//		return property;
//		
//	}
	
	
	@Override
	public Boolean checkBusinessEligibility(ConsumerBusinessRequest consumerBusinessRequest){
		Boolean check = false;
		
		BusinessMaster businessMaster = businessMasterRepo.findByBusinesscategoryAndBusinesstype(
				consumerBusinessRequest.getBusinesscategory(), consumerBusinessRequest.getBusinesstype());
		if(businessMaster==null)
		{
			return check;
		}
		if (businessMaster.getTotalemployees() <= consumerBusinessRequest.getTotalemployees()
				|| businessMaster.getBusinessage() <= consumerBusinessRequest.getBusinessage()) {
			check = true;
		}
		return check;
	}

	@Override
	public Boolean checkPropertyEligibility(String propertytype, String insurancetype, String buildingtype, Long buildingage){
		Boolean check = false;
		PropertyMaster propertyMaster = propertyMasterRepo.findByPropertytypeAndInsurancetypeAndBuildingtype(
				propertytype, insurancetype,
				buildingtype);
		if(propertyMaster==null)
		{
			return check;
		}
		if (propertyMaster.getBuildingage() <= buildingage) {
			check = true;
		}
		return check;
	}


	@Override
	public Property viewConsumerProperty(long consumerId, Long propertyid) {
		Property property = propertyRepo.findByConsumerId(consumerId);
		
		return property;
	}

	
	

}
