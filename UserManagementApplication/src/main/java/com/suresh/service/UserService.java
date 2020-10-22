package com.suresh.service;

import java.util.Map;

import com.suresh.domain.UserAccount;

public interface UserService {
	
	public String loginCheck(String email,String password);
	
	public Boolean isUniqueEmail(String email);
	public Map<Integer,String> loadCountry();
	public Map<Integer,String> loadStatesByCountryId(Integer countryId);
	public Map<Integer,String> loadCitiesByStateId(Integer StateId);
	public String generateTempPwd();
	public Boolean saveUserForm(UserAccount user);
	public String getRegSuccMailBody(UserAccount user);
	public Boolean sentRegSuccMail(String to,String subject,String body);
	
	public Boolean isTempPwdValid(String email,String password);
	public Boolean unlockAccount(String email,String password);
	public String recoveryPassword(String email);
	public String getRecoverPwdEmailBody(UserAccount userAccount);
	public String sentRecoveryPwdToMail(String to,String subject,String body);
	
	

}
