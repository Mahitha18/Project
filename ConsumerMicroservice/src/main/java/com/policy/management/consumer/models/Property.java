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
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "Business_ID")
	private Long businessId;

	@Column(name = "Consumer_ID")
	private Long consumerId;

	@Column(name = "Insurance_Type")
	private String insurancetype;
	
	@Column(name = "Property_Type")
	private String propertytype;

	@Column(name = "Building_Sqft")
	private String buildingsqft;

	@Column(name = "Building_Type")
	private String buildingtype;

	@Column(name = "Building_Storeys")
	private String buildingstoreys;

	@Column(name = "Building_Age")
	private Long buildingage;

	@Column(name = "Property_Value")
	private Long propertyvalue;

	
	@Column(name = "Cost_of_the_asset")
	private Long costoftheasset;

	
	@Column(name = "Salvage_value")
	private Long salvagevalue;

	
	@Column(name = "Useful_Life_of_the_Asset")
	private Long usefullifeoftheAsset;


	public Property(Long businessId, Long consumerId, String insurancetype, String propertytype, String buildingsqft,
			String buildingtype, String buildingstoreys, Long buildingage, Long propertyvalue, Long costoftheasset,
			Long salvagevalue, Long usefullifeoftheAsset) {
		super();
		this.businessId = businessId;
		this.consumerId = consumerId;
		this.insurancetype = insurancetype;
		this.propertytype = propertytype;
		this.buildingsqft = buildingsqft;
		this.buildingtype = buildingtype;
		this.buildingstoreys = buildingstoreys;
		this.buildingage = buildingage;
		this.propertyvalue = propertyvalue;
		this.costoftheasset = costoftheasset;
		this.salvagevalue = salvagevalue;
		this.usefullifeoftheAsset = usefullifeoftheAsset;
	}
	
	
    
}
