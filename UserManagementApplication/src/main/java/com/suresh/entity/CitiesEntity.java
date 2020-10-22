package com.suresh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CITIES_MASTER")
public class CitiesEntity {
	
	@Id
	@Column(name="CITY_ID")
	@GeneratedValue
	private Integer cityId;
	
	
	  @Column(name="STATE_ID")
	  private Integer stateId;
	 
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="STATE_ID",nullable = false) private StateEntity state;
	 */
	@Column(name="CITY_NAME")
	private String cityName;
	
	
	
	
	  
	 
	
}
