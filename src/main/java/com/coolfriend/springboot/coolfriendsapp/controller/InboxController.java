package com.coolfriend.springboot.coolfriendsapp.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import com.coolfriend.springboot.coolfriendsapp.entity.Comment;
import com.coolfriend.springboot.coolfriendsapp.entity.Like;
import com.coolfriend.springboot.coolfriendsapp.entity.User;
import com.coolfriend.springboot.coolfriendsapp.restservice.CommentRestService;
import com.coolfriend.springboot.coolfriendsapp.restservice.LikeRestService;
import com.coolfriend.springboot.coolfriendsapp.restservice.UserRestService;

@Controller
public class InboxController {
		
	@Autowired
	@Qualifier("likeRestServiceImpl")
	LikeRestService likeRestService;
	
	@Autowired
	@Qualifier("userRestServiceImpl")
	private UserRestService userRestService;
	
	@Autowired
	@Qualifier("commentRestServiceImpl")
	private CommentRestService commentRestService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	
	@GetMapping("/inbox")
	public String inbox(@RequestParam("id")int theId,
			Model theModel) {
		
		User theUser = userRestService.getUser(theId);
		List<Like> theLikes = likeRestService.getLiked(theId);
		
		List<Comment> theComments = commentRestService.getCommenteds(theId);
		
		theModel.addAttribute("theUser", theUser);
		theModel.addAttribute("theLikes", theLikes);
		theModel.addAttribute("theComments", theComments);
		
		
		//Add comments:
		System.out.println("inbox, theUser: " + theUser);
		System.out.println("inbox, theLikes: " + theLikes);
		System.out.println("inbox, theComments: " + theComments);
		
		return "user-inbox";
		
	}
	

}
