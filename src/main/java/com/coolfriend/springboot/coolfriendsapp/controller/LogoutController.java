package com.coolfriend.springboot.coolfriendsapp.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;
import com.coolfriend.springboot.coolfriendsapp.restservice.LoginoutHistoryRestService;


@Controller
public class LogoutController {
	@Autowired
	@Qualifier("loginoutHistoryRestServiceImpl")
	LoginoutHistoryRestService loginoutHistoryRestService;
	
	@GetMapping("/logout")
	public void Logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String name = (String) session.getAttribute("name");
		String type = (String) session.getAttribute("type");
		String user_id = (String) session.getAttribute("user_id");
		
		System.out.println("Logout, name: " + name);
		System.out.println("Logout, type: " + type);
		System.out.println("Logout, user_id: " + user_id);
		
		
		try {
			session.setAttribute("email", null);
			session.setAttribute("name", null);
			session.setAttribute("user_id", null);
			
			
			System.out.println("Logout, invalidating...");
			session.invalidate();
			System.out.println("Logout, successfully logged out");
			

			LoginoutHistory loginoutHistory = new LoginoutHistory();
			System.out.println("Logout, loginoutHistory.setEventType: logout");
			loginoutHistory.setEventType("logout");
			System.out.println("Logout, loginoutHistory.setId: " + Integer.parseInt(user_id));
			loginoutHistory.setUser_id(Integer.parseInt(user_id));
			
			
			String IPAddr = request.getRemoteAddr();
			System.out.println("loginProcess, loginoutHistory.setIPAddr: " + IPAddr);
			loginoutHistory.setIPAddress(IPAddr);
			
			Date currentTime = new Date();
			System.out.println("Logout, loginoutHistory.setDateTime: " + currentTime.toString());
			loginoutHistory.setDateTime(currentTime);
			
			System.out.println("Logout, loginoutHistoryRestService.saveLoginoutHistory... ");
			loginoutHistoryRestService.saveLoginoutHistory(loginoutHistory);
			
			response.sendRedirect("login");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
