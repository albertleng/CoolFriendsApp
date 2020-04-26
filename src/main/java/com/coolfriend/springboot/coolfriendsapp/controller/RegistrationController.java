package com.coolfriend.springboot.coolfriendsapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coolfriend.springboot.coolfriendsapp.entity.User;
import com.coolfriend.springboot.coolfriendsapp.restservice.UserRestService;



@Controller
public class RegistrationController {
	
	@Autowired
	@Qualifier("userRestServiceImpl")
	private UserRestService userRestService;
	
	@GetMapping("/register")
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("user", new User());
		
		return mv;
	}
	
	@PostMapping("/registerProcess")
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		
		System.out.println("registerProcess, username: " + user.getName());
		System.out.println("registerProcess, password: " + user.getPassword());
		System.out.println("registerProcess, email: " + user.getEmail());
		System.out.println("registerProcess, contact_no: " + user.getContact());
	
		System.out.println("registrationProcess, binding results: " + bindingResult);
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("register");
		}
		else {
			
			userRestService.saveUser(user);
			return new ModelAndView("welcome", "name", user.getName());
		}

	}
	 
	
	
}
