package com.coolfriend.springboot.coolfriendsapp.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coolfriend.springboot.coolfriendsapp.entity.Login;
import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;
import com.coolfriend.springboot.coolfriendsapp.entity.User;
import com.coolfriend.springboot.coolfriendsapp.restservice.LoginoutHistoryRestService;
import com.coolfriend.springboot.coolfriendsapp.restservice.UserRestService;



@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("userRestServiceImpl")
	private UserRestService userRestService;
	
	@Autowired
	@Qualifier("loginoutHistoryRestServiceImpl")
	LoginoutHistoryRestService loginoutHistoryRestService;
	
	@GetMapping("/login")
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("login", new Login());
		
		return mv;
	}
	
	@PostMapping("/loginProcess")
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) {
		ModelAndView mv = null;
		
		User user = userRestService.validateUser(login);
		
		if (user != null) {
			String name = user.getName();
			String type = user.getType();
			String user_id = Integer.toString(user.getId());
			mv = new ModelAndView("home");
				
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("type", type);
			session.setAttribute("user_id", user_id);
			
	
			LoginoutHistory loginoutHistory = new LoginoutHistory();
			System.out.println("loginProcess, loginoutHistory.setEventType: login");
			loginoutHistory.setEventType("login");
			System.out.println("loginProcess, loginoutHistory.setId: " + Integer.parseInt(user_id));
			loginoutHistory.setUser_id(Integer.parseInt(user_id));
			
			Date currentTime = new Date();
			System.out.println("loginProcess, loginoutHistory.setDateTime: " + currentTime.toString());
			loginoutHistory.setDateTime(currentTime);
			
			String IPAddr = request.getRemoteAddr();
			System.out.println("loginProcess, loginoutHistory.setIPAddr: " + IPAddr);
			loginoutHistory.setIPAddress(IPAddr);
			
			System.out.println("loginProcess, loginoutHistoryRestService.saveLoginoutHistory... ");
			loginoutHistoryRestService.saveLoginoutHistory(loginoutHistory);
			
		}
		else {
			mv = new ModelAndView("login");
			mv.addObject("message", "Username or Password is wrong!!!");
		}
		
		return mv;
	}

}

