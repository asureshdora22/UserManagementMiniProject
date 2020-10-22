package com.suresh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.suresh.constants.AppConstants;
import com.suresh.domain.UnlockAccount;
import com.suresh.props.AppProperties;
import com.suresh.service.UserService;

@Controller
public class UnlockController {
	
	@Autowired
	 private UserService service;
	@Autowired
	private AppProperties props;
	
	/**
	 * This Method load Unlock Page
	 * @param email
	 * @param model
	 * @return
	 */
	@GetMapping("/unlockAccForm")
	public String loadUnlockAccForm(@RequestParam("email") String email,Model model) 
	{
		UnlockAccount unlockAcc=new UnlockAccount();
		unlockAcc.setEmailId(email);
		model.addAttribute(AppConstants.UNLOCK_ACC, unlockAcc);
		return AppConstants.UNLOCK_ACCOUNT_VIEW_NAME;
		
	}
	
	/**
	 * 
	 * @param acc
	 * @param model
	 * @return
	 */
	@PostMapping("/unlockAcc")
	public String handleSubmitBtn(@ModelAttribute("unlockAcc")UnlockAccount acc,Model model) 
	{
		String email = acc.getEmailId();
		String password = acc.getTempPassword();
		Boolean tempPwdValid = service.isTempPwdValid(email, password);
		if(tempPwdValid) {
			service.unlockAccount(acc.getEmailId(), acc.getNewPwd());
			model.addAttribute(AppConstants.SUCCESS,props.getMessage().get(AppConstants.UNLOCK_SCC_MSG_WITH_LOGIN_LINK));
		}
		else {
			model.addAttribute(AppConstants.ERROR,props.getMessage().get(AppConstants.UNLOCK_ERROR_MSG));

		}
		return AppConstants.UNLOCK_ACCOUNT_VIEW_NAME;
		
	}

}
