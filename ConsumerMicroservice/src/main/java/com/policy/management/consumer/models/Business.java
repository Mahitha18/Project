package com.policy.management.consumer.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Business {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;

	@Column(name = "Consumer_ID")
	private Long consumerId;

	
	@Column(name = "Business_Category")
	private String businesscategory;

	
	@Column(name = "Business_Type")
	private String businesstype;

	
	@Column(name = "Business_Turnover")
	private Long businessturnover;

	
	@Column(name = "Capital_Invested ")
	private Long capitalinvested;

	
	@Column(name = "Total_Employees")
	private Long totalemployees;

	
	@Column(name = "Business_Value")
	private Long businessvalue;

	
	@Column(name = "Business_Age")
	private Long businessage;


	public Business(Long consumerId, String businesscategory, String businesstype, Long businessturnover,
			Long capitalinvested, Long totalemployees, Long businessvalue, Long businessage) {
		super();
		this.consumerId = consumerId;
		this.businesscategory = businesscategory;
		this.businesstype = businesstype;
		this.businessturnover = businessturnover;
		this.capitalinvested = capitalinvested;
		this.totalemployees = totalemployees;
		this.businessvalue = businessvalue;
		this.businessage = businessage;
	}
	
	
}
