package com.coolfriend.springboot.coolfriendsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String showPage() {
		System.out.println("HomeController, showPage");
		return "home";
	}
	
	@RequestMapping("/about")
	public String showAbout() {
		System.out.println("HomeController, showAbout");
		
		return "about";
	}
}
