package com.policy.management.consumer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.policy.management.consumer.models.Consumer;
import com.policy.management.consumer.models.Property;
import com.policy.management.consumer.repos.BusinessRepo;
import com.policy.management.consumer.repos.ConsumerRepo;
import com.policy.management.consumer.repos.PropertyRepo;
import com.policy.management.consumer.requests.BusinessPropertyRequest;
import com.policy.management.consumer.requests.ConsumerBusinessRequest;
import com.policy.management.consumer.responses.BusinessPropertyDetails;
import com.policy.management.consumer.responses.ConsumerBusinessDetails;
import com.policy.management.consumer.responses.MessageResponse;
import com.policy.management.consumer.services.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	private ConsumerService cs;
	
	@Autowired
	private BusinessRepo businessRepository;

	@Autowired
	private ConsumerRepo consumerRepository;

	@Autowired
	private PropertyRepo propertyRepository;
	
	@PostMapping("/createConsumerBusiness")
	public MessageResponse saveConsumerBusiness(@RequestBody ConsumerBusinessRequest cbr) {
		if (!cs.checkBusinessEligibility(cbr)) {
			return (new MessageResponse("Sorry!!, You are Not Eligibile for Insurance"));
		}
		return cs.createConsumerBusiness(cbr);
	}
	
	@GetMapping("/getConsumer")
	public List<Consumer> get(){
		return cs.view();
	}
	
	@GetMapping("/viewConsumerBusiness/{id}")
	public ResponseEntity<?> getconsumerbusinessdetails(@PathVariable("id") long consumerid) {
		if (!consumerRepository.existsById(consumerid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(consumerid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Business Found!!"));
		}
		return ResponseEntity.ok(cs.viewConsumerBusiness(consumerid));
	}
	
	@PostMapping("/updateConsumerBusiness")
	public MessageResponse updateConsumerBusiness(@RequestBody ConsumerBusinessDetails cbd) {
		if (!consumerRepository.existsById(cbd.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(cbd.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!businessRepository.existsById(cbd.getBusinessid())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		return cs.updateConsumerBusiness(cbd);
	}
	
	@PostMapping("/createBusinessProperty")
	public MessageResponse createBusinessProperty(@RequestBody BusinessPropertyRequest bpr) {
		if (!consumerRepository.existsById(bpr.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(bpr.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!businessRepository.existsById(bpr.getBusinessId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!cs.checkPropertyEligibility(bpr.getPropertytype(),
				bpr.getInsurancetype(), bpr.getBuildingtype(),bpr.getBuildingage())) {
			return (new MessageResponse("Sorry!!, You are Not Eligibile for Insurance"));
		}
		return cs.createBusinessProperty(bpr);
	}
	
	@GetMapping("/viewConsumerProperty")
	public ResponseEntity<?> getBusinessProperty(@RequestParam long consumerid, @RequestParam long propertyid) {
		if (!propertyRepository.existsById(propertyid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Property Found!!"));
		}
		if (!consumerRepository.existsById(consumerid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(consumerid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Business Found!!"));
		}
		return ResponseEntity.ok(cs.viewConsumerProperty(consumerid,propertyid));
	}
	
	@PostMapping("/updateBusinessProperty")
	public MessageResponse updateConsumerProperty(@RequestBody BusinessPropertyDetails bpd) {
		if (!propertyRepository.existsById(bpd.getPropertyId())) {
			return (new MessageResponse("Sorry!!, No Property Found!!"));
		}
		if (!consumerRepository.existsById(bpd.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(bpd.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!businessRepository.existsById(bpd.getBusinessId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		return cs.updateBusinessProperty(bpd);
	}
	
	
	
}
