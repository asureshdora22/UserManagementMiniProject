package com.suresh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
@Data
@Entity
@Table(name="USER_ACCOUNTS")
public class UserAccountEntity {
	
	@Id
	@Column(name="USER_ID")
	@GeneratedValue
	private Integer userId;
	
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="ACCOUNT_STATUS")
    private String  accountStatus;
	
	@Column(name="CREATED_DATE")
	@CreationTimestamp
    private Date createdDate ; 
	
	@Column(name="DOB")
    private Date dob;
	
	@Column(name="FIRST_NAME")
    private String firstName; 
	
	@Column(name="LAST_NAME")
    private String lastName; 
	
	@Column(name="GENDER")
    private String gender ;
    
	@Column(name="UPDATE_DATE")
	@CreationTimestamp
    private Date updateDate; 
	
	@Column(name="USER_EMAIL")
    private String userEmail;
	
	@Column(name="USER_PWD")
    private String userPwd; 
	
	@Column(name="USER_MOBILE")
    private String userMobile ;  
}
