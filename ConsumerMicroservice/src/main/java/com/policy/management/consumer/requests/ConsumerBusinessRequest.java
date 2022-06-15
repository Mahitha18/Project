package com.policy.management.consumer.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerBusinessRequest {

	private String firstname;

	private String lastname;

	private String dob;

	private String businessname;

	private String pandetails;

	private String email;

	private String phone;

	private String website;

	private String businessoverview;

	private String validity;

	private String agentname;

	private Long agentid;

	private String businesscategory;

	private String businesstype;

	private Long businessturnover;

	private Long capitalinvested;

	private Long totalemployees;

	private Long businessvalue;

	private Long businessage;

}
