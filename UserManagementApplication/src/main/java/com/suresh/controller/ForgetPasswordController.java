package com.suresh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.suresh.constants.AppConstants;
import com.suresh.props.AppProperties;
import com.suresh.service.UserService;

@Controller
public class ForgetPasswordController {

	@Autowired
	private UserService service;
	
	@Autowired
	private AppProperties props;
	
	@GetMapping("/forgotPwdForm")
	public String loadForgetPwdForm() 
	{
		return AppConstants.FORGOT_PASSWORD_VIEW_NAME;
		
	}
	/**
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@PostMapping("/forgotPwdForm")
	public String isValidEmail(HttpServletRequest req,Model model) 
	{
		String email = req.getParameter(AppConstants.EMAIL);
		String status = service.recoveryPassword(email);
		if(status.equals(AppConstants.RECOVERY_STATUS_SUCCESS)) {
			 model.addAttribute(AppConstants.SUCCESS,props.getMessage().get(AppConstants.FORGET_SUCCESS_MSG));
		}
		else {
			 model.addAttribute(AppConstants.ERROR, props.getMessage().get(AppConstants.FORGET_ERROR_MSG));

		}
		return AppConstants.FORGOT_PASSWORD_VIEW_NAME;
		
	}
	
}
