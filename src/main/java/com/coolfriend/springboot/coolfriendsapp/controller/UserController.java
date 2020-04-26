package com.coolfriend.springboot.coolfriendsapp.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coolfriend.springboot.coolfriendsapp.entity.Comment;
import com.coolfriend.springboot.coolfriendsapp.entity.Like;
import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;
import com.coolfriend.springboot.coolfriendsapp.entity.User;
import com.coolfriend.springboot.coolfriendsapp.restservice.CommentRestService;
import com.coolfriend.springboot.coolfriendsapp.restservice.LikeRestService;
import com.coolfriend.springboot.coolfriendsapp.restservice.LoginoutHistoryRestService;
import com.coolfriend.springboot.coolfriendsapp.restservice.UserRestService;

@Controller
public class UserController {
		
	@Autowired
	@Qualifier("loginoutHistoryRestServiceImpl")
	LoginoutHistoryRestService loginoutHistoryRestService;
	
	@Autowired
	@Qualifier("userRestServiceImpl")
	private UserRestService userRestService;
	
	@Autowired
	@Qualifier("likeRestServiceImpl")
	private LikeRestService likeRestService;
	
	@Autowired
	@Qualifier("commentRestServiceImpl")
	private CommentRestService commentRestService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	

	
	@GetMapping(value= {"/list", "/manage"})
	public String listFriends(Model model) {
		
		
		
		List<User> theUsers = userRestService.getUsers();
		
		System.out.println("listFriends, theUsers:" + theUsers);
		
		model.addAttribute("users", theUsers);
		
		System.out.println("users list: " + theUsers.size());
		for(User user: theUsers) {
			System.out.println("user-name: " + user.getName());
			System.out.println("user-email: " + user.getEmail());
			System.out.println("user-country: " + user.getCountry());
			System.out.println("user-city: " + user.getCity());
			System.out.println("user-postalcode: " + user.getPostalcode());
			System.out.println("user-contact_no: " + user.getContact());
			System.out.println("user-postalcode: " + user.getPostalcode());
			System.out.println("user-type: " + user.getType());
			System.out.println("user-password:" + user.getPassword());
			System.out.println("user-dob: " + user.getDob());
			System.out.println("user-gender:" + user.getGender());
			
		}
		
		return "list-users";
		
	}
	
	@GetMapping("/add")
	public String add(Model theModel) {
		
		// create model attribute to bind form data
		User theUser = new User();
		
		theModel.addAttribute("user", theUser);
		return "user-form";
	}
	
	
	@PostMapping("/save") 
	public String saveUser(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		
		System.out.println("saveUser, username: " + user.getName());
		System.out.println("saveUser, password: " + user.getPassword());
		System.out.println("saveUser, email: " + user.getEmail());
		System.out.println("saveUser, contact_no: " + user.getContact());
	
		System.out.println("saveUser, binding results: " + bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "user-form";
		}
		else {
			
			userRestService.saveUser(user);
			return "redirect:/list";
		}
		
		
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id")int theId,
			Model theModel) {
		
		User theUser = userRestService.getUser(theId);
		LoginoutHistory loginoutHistory = loginoutHistoryRestService.getLastLoginHistory(theId);
		
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("loginouthistory", loginoutHistory);
		
		return "user-form";
		
	}
	
	@GetMapping("/profile")
	public String profile(@RequestParam("id")int theId, HttpServletRequest request,
			Model theModel) {
		
		HttpSession session = request.getSession();
		String likeId =  (String) session.getAttribute("user_id");
		
		User theLikedUser = userRestService.getUser(theId);
		User theUser = userRestService.getUser(theId);
		List<Like> theLikeds = likeRestService.getLiked(theId);
		List<Like> theLikeAndLikeds = likeRestService.getLikeAndLiked(Integer.parseInt(likeId), theId);
		// Get list of comments
		List<Comment> theCommenteds = commentRestService.getCommenteds(theId);
		
		theModel.addAttribute("theUser", theUser);
		theModel.addAttribute("theUser", theLikedUser);
		theModel.addAttribute("theLikeds", theLikeds);
		theModel.addAttribute("theLikeAndLikeds", theLikeAndLikeds);
		theModel.addAttribute("theCommenteds", theCommenteds);
		
		
		//Add comments:
		System.out.println("profile, theUser: " + theUser);
		
		return "user-profile";
		
	}
	
	@GetMapping("/delete") 
	public String delete(@RequestParam("id") int theId) {
		
		userRestService.deleteUser(theId);
		return "redirect:/list";
		
	}
	
	@GetMapping("/loginhistory") 
	public String loginHistory(@RequestParam("id") int theId, Model theModel) {
		
		List<LoginoutHistory> theHistories= loginoutHistoryRestService.getLoginoutHistories(theId);
		User theUser = userRestService.getUser(theId);
		
		theModel.addAttribute("histories", theHistories);
		theModel.addAttribute("user", theUser);
		
		
		return "list-histories";
		
	}
	
	@PostMapping("/search")
    public String searchUsers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {

        // search users from the service
        List<User> theUsers = userRestService.searchUsers(theSearchName);
                
        // add the users to the model
        theModel.addAttribute("users", theUsers);

        return "list-users";        
    }
	
	
	

}
