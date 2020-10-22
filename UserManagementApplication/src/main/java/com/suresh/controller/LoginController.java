package com.suresh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.suresh.constants.AppConstants;
import com.suresh.service.UserService;
@Controller
public class LoginController {

	@Autowired
	private UserService service;
	/**
	 * 
	 * @return 
	 */
	@GetMapping(value= {"/","/login"})
	public String loadLoginForm() {
		return AppConstants.LOGIN_VIEW_NAME;
	}
	/**
	 * HttpServletRequest capture the data like mail &pwd
	 * @param req
	 * @param model
	 * @return
	 */
	
	@PostMapping("/handleLoginBtn")
	public String handleLoginBtn(HttpServletRequest req,Model model)
	{
		String viewname=AppConstants.EMPTY_VIEW_NAME;
		String email = req.getParameter(AppConstants.EMAIL);
		String pwd = req.getParameter(AppConstants.PAZZWORD);
		String status = service.loginCheck(email, pwd);
		if (status.equals(AppConstants.VALID)) {
			viewname=AppConstants.DASHBOARD_VIEW_NAME;
			
		} 
		else {
			viewname=AppConstants.LOGIN_VIEW_NAME;
			model.addAttribute(AppConstants.ERROR, status);
		}
		return viewname;
		
	}

}
