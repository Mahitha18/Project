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
public class Consumer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private long id;
	@Column(name = "First_Name")
	private String firstname;
	
	
	@Column(name = "Last_Name")
	private String lastname;
	
	
	@Column(name = "DOB")
	private String dob;
	
	
	@Column(name = "Business_Name")
	private String businessname;
	
	
	@Column(name = "PAN_Details")
	private String pandetails;
	
	
	@Column(name = "Email")
	private String email;
	
	
	@Column(name = "Phone")
	private String phone;
	

	@Column(name = "Website")
	private String website;
	
	
	@Column(name = "Business_Overview")
	private String businessoverview;
	
	
	@Column(name = "Validity_of_Consumer")
	private String validity;
	
	
	@Column(name = "Agent_Name")
	private String agentname;
	
	
	@Column(name = "Agent_ID")
	private Long agentid;


	public Consumer(String firstname, String lastname, String dob, String businessname, String pandetails, String email,
			String phone, String website, String businessoverview, String validity, String agentname, Long agentid) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.businessname = businessname;
		this.pandetails = pandetails;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.businessoverview = businessoverview;
		this.validity = validity;
		this.agentname = agentname;
		this.agentid = agentid;
	}
	
	
	
}
