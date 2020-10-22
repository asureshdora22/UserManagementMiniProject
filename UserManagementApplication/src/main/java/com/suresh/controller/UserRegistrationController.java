package com.suresh.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suresh.constants.AppConstants;
import com.suresh.domain.UserAccount;
import com.suresh.props.AppProperties;
import com.suresh.service.UserService;

@Controller
public class UserRegistrationController {

	@Autowired
	private UserService service;
	
	@Autowired
	private AppProperties props;
	
	@ModelAttribute
	public void loadFormData(Model model) {
		model.addAttribute(AppConstants.USER_ACC,new UserAccount());
		model.addAttribute(AppConstants.LIST_OF_COUNTRY,service.loadCountry());
	}
	/**
	 * This Form is Load Registration Form
	 * @param model
	 * @return
	 */
	@GetMapping("/loadRegForm")
	public String loadRegistrationForm() {
		
		return AppConstants.REGISTRATION_VIEW_NAME;
	}
	
	/**
	 * @ResponseBody because we send row data not presentation
	 * @param email
	 * @return
	 */
	@GetMapping("/uniqueMail")
	public @ResponseBody String isUniqueEmail(@RequestParam("email") String email) {
		
		return service.isUniqueEmail(email) ? AppConstants.UNIQUE : AppConstants.DUPLICATE;
			 
		
		
		}
	
	/**
	 * Map<Integer,String> returntype StateId ,StateName
	 * @param countryId
	 * @return
	 */
	@GetMapping("/countryChange")
	public @ResponseBody Map<Integer,String> handleCountryChangeEvnt(@RequestParam("countryId") Integer countryId) {
		return service.loadStatesByCountryId(countryId);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/changeState")
	public @ResponseBody Map<Integer,String> handleStateChangeEvnt(@RequestParam("stateId") Integer stateId) {
		return service.loadCitiesByStateId(stateId);
	}
	
	/**
	 * 
	 * @param user
	 * @param att
	 * @return
	 */
	
	@PostMapping("/userAccount")
	public String saveRegiForm(UserAccount user,RedirectAttributes ree) {
		Boolean saveUserForm = service.saveUserForm(user);
		if(saveUserForm) {
			
			ree.addFlashAttribute(AppConstants.SUCCESS,props.getMessage().get(AppConstants.REG_SUCCESS_MSG));
			
		}else
		{
			ree.addFlashAttribute(AppConstants.ERROR,props.getMessage().get(AppConstants.REG_ERR_MSG));
			
		}
		
		return "redirect:loadRegForm";
	}

	
}
