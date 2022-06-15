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
public class BusinessMaster {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;

	@Column(name = "Business_Category")
	private String businesscategory;

	@Column(name = "Business_Type")
	private String businesstype;
	
	@Column(name = "Total_Employees")
	private Long totalemployees;
	
	@Column(name = "Business_Age")
	private Long businessage;
}
