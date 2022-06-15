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
public class PropertyMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;

	
	@Column(name = "Insurance_Type")
	private String insurancetype;
	
	@Column(name = "Property_Type")
	private String propertytype;

	@Column(name = "Building_Type")
	private String buildingtype;

	@Column(name = "Building_Age")
	private Long buildingage;

}
