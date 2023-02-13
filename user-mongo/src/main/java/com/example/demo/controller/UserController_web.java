package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;

@Controller
public class UserController_web {
	@Autowired
	UserRepo repo;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user) {
		repo.insert(user);
		return "home.jsp";
	}
	
	@RequestMapping("/getUser")
	public ModelAndView getUser(@RequestParam int id) {
		ModelAndView mv= new ModelAndView("showUser.jsp");
		User user=repo.findById(id).orElse(new User(0,"no such user"));
		mv.addObject(user);
		return mv;
	}
}
