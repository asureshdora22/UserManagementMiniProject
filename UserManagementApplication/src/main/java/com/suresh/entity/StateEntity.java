package com.suresh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="STATES_MASTER")
public class StateEntity {
	@Id
	@GeneratedValue
	@Column(name="STATE_ID")
	private Integer stateId;
	
	
	  @Column(name="COUNTRY_ID") 
	  private Integer countryId;
	 
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="COUNTRY_ID",nullable = false) private CountryEntity
	 * country;
	 */
	
}
