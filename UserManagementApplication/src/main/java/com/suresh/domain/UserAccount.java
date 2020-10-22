package com.suresh.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserAccount {
	    private Integer userId;
	    private String  accountStatus;       
	    private Integer  cityId;                
	    private Integer  countryId ;            
	    private Date createdDate ;         
	    private Date dob;                  
	    private String firstName;             
	    private String lastName;              
	    private String gender ;                
	    private Integer stateId ;             
	    private Date updateDate;            
	    private String userEmail;            
	    private String userPwd;               
	    private String userMobile ;           
}
