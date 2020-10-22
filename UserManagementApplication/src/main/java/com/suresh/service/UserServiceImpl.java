package com.suresh.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suresh.constants.AppConstants;
import com.suresh.domain.UserAccount;
import com.suresh.entity.CitiesEntity;
import com.suresh.entity.CountryEntity;
import com.suresh.entity.StateEntity;
import com.suresh.entity.UserAccountEntity;
import com.suresh.repository.CitiesRepository;
import com.suresh.repository.CountryRepository;
import com.suresh.repository.StatesRepository;
import com.suresh.repository.UserAccountRepository;
import com.suresh.utils.SendMailUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SendMailUtils send;

	@Autowired
	private UserAccountRepository userRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private StatesRepository stateRepo;
	
	@Autowired
	private CitiesRepository citiesRepo;
	
	@Override
	public String loginCheck(String email, String password) {
		UserAccountEntity entity = userRepo.findByUserEmailAndUserPwd(email, password);
		if (entity==null) {
			return AppConstants.INVALID_CREDENTIALS_MSG;
		}else if (entity.getAccountStatus().equals(AppConstants.USER_ACC_LOCKED)) {
			return AppConstants.ACCOUNT_LOCKED_MSG;
		}
		else {
			return AppConstants.VALID;
		}
	}

	@Override
	public Boolean isUniqueEmail(String email) {
		
		UserAccountEntity userEmail = userRepo.findByUserEmail(email);
		return userEmail!=null ? false : true;
	}

	@Override
	public Map<Integer, String> loadCountry() {
		Map<Integer,String> map=new HashMap<Integer, String>();
		 List<CountryEntity> listEntity = countryRepo.findAll();
		 listEntity.forEach(list->{
			 map.put(list.getCountryId(),list.getCountryName());
		 });
		return map;
	}

	@Override
	public Map<Integer, String> loadStatesByCountryId(Integer countryId) {
		Map<Integer,String> map=new HashMap<Integer, String>();

		List<StateEntity> listEntity = stateRepo.findByCountryId(countryId);
		listEntity.forEach(list->{
			 map.put(list.getStateId(),list.getStateName());
		 });
		return map;	}

	@Override
	public Map<Integer, String> loadCitiesByStateId(Integer stateId) {
		
		Map<Integer,String> map=new HashMap<Integer, String>();

		List<CitiesEntity> listEntity = citiesRepo.findByStateId(stateId);
		listEntity.forEach(list->{
			 map.put(list.getCityId(),list.getCityName());
		 });
		return map;
	}

	@Override
	public String generateTempPwd() {
		// chose a Character random from this String 
        String AlphaNumericString = AppConstants.ALPHA_NUMERIC_STRING; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(6); 
  
        for (int i = 0; i < 6; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
		
	}

	@Override
	public Boolean saveUserForm(UserAccount user) {
		user.setAccountStatus(AppConstants.USER_ACC_LOCKED);
		user.setUserPwd(generateTempPwd());
		UserAccountEntity entity=new UserAccountEntity();
		BeanUtils.copyProperties(user, entity);
		UserAccountEntity save = userRepo.save(entity);
		
		if(save.getUserId()!=null) {
			String to = user.getUserEmail();
			String subject=AppConstants.SUBJECT_ACC_SCC;
			String body = getRegSuccMailBody(user);
			sentRegSuccMail(to,subject,body);
			return true;
		}
		
		
		return false;
	}

	@Override
	public String getRegSuccMailBody(UserAccount user) {
		
		String fileName =AppConstants.UNLOCK_ACCOUNT_EMAIL_FILE;
		List<String> replacedLines = null;
		String mailBody = null;
		try {
		Path path = Paths.get(fileName, "");
		Stream<String> lines = Files.lines(path);
		
		replacedLines = lines.map(line -> line.replace(AppConstants.FIRST_NAME, user.getFirstName())
												.replace(AppConstants.LAST_NAME, user.getLastName())
												.replace(AppConstants.TEMP_PWD, user.getUserPwd())
												.replace(AppConstants.MAIL, user.getUserEmail()))
												.collect(Collectors.toList());
		mailBody = String.join("", replacedLines);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return mailBody;

	}

	@Override
	public Boolean sentRegSuccMail(String to, String subject, String body) {
		
		return send.sendMail(to, subject, body);
	}

	@Override
	public Boolean isTempPwdValid(String email, String password) {
		UserAccountEntity userAcc = userRepo.findByUserEmailAndUserPwd(email, password);
		return userAcc!=null?true:false;
	}

	@Override
	public Boolean unlockAccount(String email, String password) {
		UserAccountEntity entity = userRepo.findByUserEmail(email);
		entity.setAccountStatus("UNLOCKED");
		entity.setUserPwd(password);
		UserAccountEntity saveentity = userRepo.save(entity);
		return saveentity!=null?true:false;
	}

	@Override
	public String recoveryPassword(String email) {
		UserAccountEntity entity = userRepo.findByUserEmail(email);
		if(entity!=null) {
			UserAccount acc=new UserAccount();
			BeanUtils.copyProperties(entity, acc);
			String to = acc.getUserEmail();
			String subject=AppConstants.SUBJECT_RECOVERY_SCC;
			String body=getRecoverPwdEmailBody(acc);
			return sentRecoveryPwdToMail(to, subject, body);
			 

		}
		else {
			return AppConstants.FAILED;
		}
		
	}

	@Override
	public String getRecoverPwdEmailBody(UserAccount user) {
		
		String fileName = AppConstants.PAZZWORD_RECOVERY_EMAIL_FILE;;
		List<String> replacedLines = null;
		String mailBody = null;
		try {
		Path path = Paths.get(fileName, "");
		Stream<String> lines = Files.lines(path);
		
		replacedLines = lines.map(line -> line.replace(AppConstants.FIRST_NAME, user.getFirstName())
												.replace(AppConstants.LAST_NAME, user.getLastName())
												.replace(AppConstants.PWD, user.getUserPwd()))
												.collect(Collectors.toList());
		mailBody = String.join("", replacedLines);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public String sentRecoveryPwdToMail(String to, String subject, String body) {
		Boolean isSent = send.sendMail(to, subject, body);
		if (isSent) {
			return AppConstants.ISSENT_SUCCESS;
		}
		return AppConstants.ISSENT_FAILED;
	}

	
}
